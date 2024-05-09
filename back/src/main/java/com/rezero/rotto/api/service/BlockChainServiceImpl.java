package com.rezero.rotto.api.service;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.StaticGasProvider;

import com.rezero.rotto.contracts.TokenManager;
import com.rezero.rotto.dto.request.CreateTokenRequest;
import com.rezero.rotto.dto.request.PayTokensRequest;
import com.rezero.rotto.dto.request.RefundsTokenRequest;
import com.rezero.rotto.entity.Subscription;
import com.rezero.rotto.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlockChainServiceImpl implements BlockChainService{
	@Autowired
	private final Web3j web3j;

	@Autowired
	private final Credentials credentials;

	@Autowired
	private final SubscriptionRepository subscriptionRepository;

	private TokenManager tokenManager = null;

	@Value("${CHAIN_TOKEN_MANAGER}")
	private String tokenManagerAddress;

	private static final BigInteger GAS_LIMIT = BigInteger.valueOf(9_007_199_254_740_991L);
	private static final BigInteger GAS_PRICE = BigInteger.ZERO;

	@Override
	public ResponseEntity<?> createToken(CreateTokenRequest request) throws Exception{
		if(tokenManager == null) initContract();
		Subscription subscription = subscriptionRepository.findBySubscriptionCode(request.getCode());
		if(subscription == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("요청하신 청약을 찾을 수 없습니다.");

		TokenManager.Subscription requestSubscription = changeVariable(subscription);
		BigInteger amount = BigInteger.valueOf(request.getAmount());

		TransactionReceipt transactionReceipt = tokenManager.createToken(requestSubscription, amount).send();
		if(!transactionReceipt.isStatusOK()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("토큰 생성 실패");
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Override
	public ResponseEntity<?> distributeToken(PayTokensRequest request) throws Exception{
		if(tokenManager == null) initContract();

		Subscription subscription = subscriptionRepository.findBySubscriptionCode(request.getCode());
		if(subscription == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("요청하신 청약을 찾을 수 없습니다.");
		else if(request.getAmount() > subscription.getLimitNum())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("요청하신 수량이 1인당 가질 수 있는 개수를 초과하였습니다.");

		// 이전에 금융망을 통해 사용자 ssafy 계좌 이체 요청 필요

		// BigInteger code = BigInteger.valueOf(subscription.getSubscriptionCode());
		TokenManager.Subscription requestSubscription = changeVariable(subscription);
		BigInteger amount = BigInteger.valueOf(request.getAmount());


		// CompletableFuture<TransactionReceipt> transactionReceipt = tokenManager.distributeToken(code, request.getAddress(), amount).sendAsync();
		// transactionReceipt.thenAccept(receipt -> {
		// 	// 거래장부에 거래내역 추가
		// }).exceptionally(e -> {
		// 	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("토큰 발급에 실패하였습니다.");
		// });
		TransactionReceipt transactionReceipt = tokenManager.distributeToken(requestSubscription, request.getAddress(), amount).send();
		if(!transactionReceipt.isStatusOK())    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("토큰 발급에 실패하였습니다.");
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<?> RefundsToken(RefundsTokenRequest request) throws Exception{
		if(tokenManager == null) initContract();

		Subscription subscription = subscriptionRepository.findBySubscriptionCode(request.getCode());
		if(subscription == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("요청하신 청약을 찾을 수 없습니다.");

		BigInteger code = BigInteger.valueOf(subscription.getSubscriptionCode());
		TransactionReceipt transactionReceipt = tokenManager.deleteToken(code, request.getAddress()).send();
		if(!transactionReceipt.isStatusOK())    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("환급 실패");

		// 거래 장부에 거래 내역 추가

		// 금융망에 사용자의 계좌에 입금 필요

		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<?> InsertWhiteList(String wallet) throws Exception {
		if(tokenManager == null) initContract();

		TransactionReceipt transactionReceipt = tokenManager.insertList(wallet).send();
		if(!transactionReceipt.isStatusOK())    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("list 추가 작업 실패");

		return ResponseEntity.ok().body("list 추가 작업 완료");
	}

	@Override
	public ResponseEntity<?> RemoveWhiteList(String wallet) throws Exception {
		if(tokenManager == null) initContract();

		TransactionReceipt transactionReceipt = tokenManager.removeList(wallet).send();
		if(!transactionReceipt.isStatusOK())    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("list 제거 작업 실패");

		return ResponseEntity.ok().body("list 제거 작업 완료");
	}

	private TokenManager.Subscription changeVariable(Subscription subscription){
		BigInteger code = BigInteger.valueOf(subscription.getSubscriptionCode());
		BigInteger confirm_price = BigInteger.valueOf(subscription.getConfirmPrice());
		BigInteger limit_num = BigInteger.valueOf(subscription.getLimitNum());

		TokenManager.Subscription newSubscription = new TokenManager.Subscription(code, confirm_price, limit_num);
		return newSubscription;
	}

	private void initContract() {
		this.tokenManager = TokenManager.load(tokenManagerAddress, web3j, credentials, new StaticGasProvider(GAS_PRICE, GAS_LIMIT));
	}
}
