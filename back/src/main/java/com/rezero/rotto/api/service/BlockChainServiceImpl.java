package com.rezero.rotto.api.service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.gas.StaticGasProvider;

import com.google.api.Http;
import com.rezero.rotto.contracts.TokenManager;
import com.rezero.rotto.dto.request.CreateTokenRequest;
import com.rezero.rotto.dto.request.PayTokensRequest;
import com.rezero.rotto.dto.request.RefundsTokenRequest;
import com.rezero.rotto.entity.Subscription;
import com.rezero.rotto.entity.TradeHistory;
import com.rezero.rotto.repository.SubscriptionRepository;
import com.rezero.rotto.repository.TradeHistoryRepository;

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

	@Autowired
	private final TradeHistoryRepository tradeHistoryRepository;

	private TokenManager tokenManager = null;

	@Value("${CHAIN_TOKEN_MANAGER}")
	private String tokenManagerAddress;

	private final Logger logger = LoggerFactory.getLogger(BlockChainServiceImpl.class);
	private static final BigInteger GAS_LIMIT = BigInteger.valueOf(9_007_199_254_740_991L);
	private static final BigInteger GAS_PRICE = BigInteger.ZERO;

	@Override
	public ResponseEntity<?> createToken(CreateTokenRequest request) {
		if(tokenManager == null) initContract();
		Subscription subscription = subscriptionRepository.findBySubscriptionCode(request.getCode());
		if(subscription == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("요청하신 청약을 찾을 수 없습니다.");

		TokenManager.Subscription requestSubscription = changeVariable(subscription);
		BigInteger amount = BigInteger.valueOf(request.getAmount());

		CompletableFuture<TransactionReceipt> transactionReceiptFuture =
			tokenManager.createToken(requestSubscription, amount).sendAsync();

		try {
			TransactionReceipt transactionReceipt = transactionReceiptFuture.join();
			if(transactionReceipt.isStatusOK())
				return ResponseEntity.status(HttpStatus.CREATED).body("ROTTO 생성 완료");
			else
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ROTTO 생성 실패");
		} catch (Exception ex){
			Throwable cause = ex.getCause();
			if(cause instanceof TransactionException){
					String revertReason = getRevertReason((TransactionException)cause);
					return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(revertReason);
			}
			String errorMessage = (cause != null ? cause.getMessage() : ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		}
	}

	@Override
	public ResponseEntity<?> distributeToken(PayTokensRequest request) {
		if(tokenManager == null) initContract();

		Subscription subscription = subscriptionRepository.findBySubscriptionCode(request.getCode());
		if(subscription == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("요청하신 청약을 찾을 수 없습니다.");
		else if(request.getAmount() > subscription.getLimitNum())
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("요청하신 수량이 1인당 가질 수 있는 개수를 초과하였습니다.");

		// 이전에 금융망을 통해 사용자 ssafy 계좌 이체 요청 필요

		// BigInteger code = BigInteger.valueOf(subscription.getSubscriptionCode());
		TokenManager.Subscription requestSubscription = changeVariable(subscription);
		BigInteger amount = BigInteger.valueOf(request.getAmount());

		CompletableFuture<TransactionReceipt> transactionReceiptFuture
			= tokenManager.distributeToken(requestSubscription, request.getAddress(), amount).sendAsync();

		try {
			TransactionReceipt transactionReceipt = transactionReceiptFuture.join();
			if(transactionReceipt.isStatusOK())
				return ResponseEntity.status(HttpStatus.OK).body("ROTTO 발급 완료");
			else
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ROTTO 발급 실패");
		} catch (Exception ex){
			Throwable cause = ex.getCause();
			if(cause instanceof TransactionException){
				String revertReason = getRevertReason((TransactionException)cause);
				logger.info("[distributeToken] revertReason: " + revertReason);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(revertReason);
			}
			String errorMessage = (cause != null ? cause.getMessage() : ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		}
	}

	@Override
	public ResponseEntity<?> RefundsToken(RefundsTokenRequest request) {
		if(tokenManager == null) initContract();

		Subscription subscription = subscriptionRepository.findBySubscriptionCode(request.getCode());
		if(subscription == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("요청하신 청약을 찾을 수 없습니다.");

		BigInteger code = BigInteger.valueOf(subscription.getSubscriptionCode());

		CompletableFuture<TransactionReceipt> transactionReceiptFuture
			= tokenManager.deleteToken(code, request.getAddress()).sendAsync();
		try {
			TransactionReceipt transactionReceipt = transactionReceiptFuture.join();
			if(transactionReceipt.isStatusOK())
				return ResponseEntity.status(HttpStatus.OK).body("ROTTO 환급 완료");
			else
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ROTTO 환급 실패");
		} catch (Exception ex){
			Throwable cause = ex.getCause();
			if(cause instanceof TransactionException){
				String revertReason = getRevertReason((TransactionException)cause);
				logger.info("[RefundsToken] revertReason: " + revertReason);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(revertReason);
			}
			String errorMessage = (cause != null ? cause.getMessage() : ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		}
		// 거래 장부에 거래 내역 추가

		// 금융망에 사용자의 계좌에 입금 필요

	}

	@Override
	public ResponseEntity<?> InsertWhiteList(String wallet){
		if(tokenManager == null) initContract();
		CompletableFuture<TransactionReceipt> transactionReceiptFuture = tokenManager.insertList(wallet).sendAsync();

		try{
			TransactionReceipt transactionReceipt = transactionReceiptFuture.join();
			if(transactionReceipt.isStatusOK())
				return ResponseEntity.ok().body("list 추가 작업 완료");
			else
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("list 추가 작업 실패");
		} catch (Exception ex) {
			Throwable cause = ex.getCause();
			if(cause instanceof TransactionException){
				String revertReason = getRevertReason((TransactionException)cause);
				logger.info("[InsertWhiteList] revertReason: " + revertReason);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(revertReason);
			}
			String errorMessage = (cause != null ? cause.getMessage() : ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		}
	}

	@Override
	public ResponseEntity<?> RemoveWhiteList(String wallet){
		if(tokenManager == null) initContract();
		CompletableFuture<TransactionReceipt> transactionReceiptFuture = tokenManager.removeList(wallet).sendAsync();

		try{
			TransactionReceipt transactionReceipt = transactionReceiptFuture.join();
			if(transactionReceipt.isStatusOK())
				return ResponseEntity.ok().body("list 제거 작업 완료");
			else
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("list 제거 작업 실패");
		} catch (Exception ex) {
			Throwable cause = ex.getCause();
			if(cause instanceof TransactionException){
				String revertReason = getRevertReason((TransactionException)cause);
				logger.info("[RemoveWhiteList] revertReason: " + revertReason);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(revertReason);
			}
			String errorMessage = (cause != null ? cause.getMessage() : ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		}
	}

	private TokenManager.Subscription changeVariable(Subscription subscription){
		BigInteger code = BigInteger.valueOf(subscription.getSubscriptionCode());
		BigInteger confirm_price = BigInteger.valueOf(subscription.getConfirmPrice());
		BigInteger limit_num = BigInteger.valueOf(subscription.getLimitNum());

		return new TokenManager.Subscription(code, confirm_price, limit_num);
	}

	private String decodeRevertMessage(String hexEncoded) {
		String errorMethodId = "0x08c379a0";
		logger.info("[decodeRevertMessage] hexEncoded: " + hexEncoded);
		if(hexEncoded.startsWith(errorMethodId)){
			return hexToASCII(hexEncoded.substring(132).trim());
		}
		return "Transaction failed";
	}

	private String hexToASCII(String hexStr){
		HexFormat hexFormat = HexFormat.of();

		// 16진수 문자열을 바이트 배열로 변환
		byte[] bytes = hexFormat.parseHex(hexStr);

		// 바이트 배열을 UTF-8 문자열로 디코딩
		String decodedString = new String(bytes, StandardCharsets.UTF_8);

		return decodedString.replace("\u0000", "").substring(1);
	}

	private String getRevertReason(TransactionException e)  {
		if(e.getTransactionReceipt().isPresent()){
			TransactionReceipt receipt = e.getTransactionReceipt().get();
			String revertReason = receipt.getRevertReason();
			logger.info("[getRevertReason] revertReason: " + revertReason);
			if(revertReason != null && !revertReason.isEmpty()){
				return decodeRevertMessage(revertReason);
			}
		}
		return "No revert reason provided.";
	}

	private void initContract() {
		this.tokenManager = TokenManager.load(tokenManagerAddress, web3j, credentials, new StaticGasProvider(GAS_PRICE, GAS_LIMIT));
	}
}
