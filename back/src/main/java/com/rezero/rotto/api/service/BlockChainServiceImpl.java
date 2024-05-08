package com.rezero.rotto.api.service;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.StaticGasProvider;

import com.rezero.rotto.contracts.TokenManager;
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

	@Value("${blockchain.CA.tokenManager}")
	private String tokenManagerAddress;

	private static final BigInteger GAS_LIMIT = BigInteger.valueOf(9_007_199_254_740_991L);
	private static final BigInteger GAS_PRICE = BigInteger.ZERO;

	@Override
	public void createToken(int subscriptionCode) throws Exception {
		if(tokenManager == null) initContract();
		Subscription subscription = subscriptionRepository.findBySubscriptionCode(subscriptionCode);
		if(subscription == null) throw new Exception();

		// TransactionReceipt receipt = tokenManager.getSubscription(changeVariable(subscription)).send();
		// TransactionReceipt receipt = tokenManager.createToken(BigInteger.valueOf(2192)).send();
	}


	private TokenManager.Subscription changeVariable(Subscription subscription){
		BigInteger code = BigInteger.valueOf(subscription.getSubscriptionCode());
		BigInteger farm_code = BigInteger.valueOf(subscription.getFarmCode());
		BigInteger confirm_price = BigInteger.valueOf(subscription.getConfirmPrice());
		BigInteger started_time = BigInteger.valueOf(0);
		BigInteger end_time = BigInteger.valueOf(10000);
		BigInteger limit_num = BigInteger.valueOf(subscription.getLimitNum());
		BigInteger return_rate = BigInteger.valueOf(subscription.getReturnRate());

		TokenManager.Subscription newSubscription = new TokenManager.Subscription(code, farm_code, confirm_price, started_time, end_time, limit_num, return_rate);
		return newSubscription;
	}

	private void initContract() {
		this.tokenManager = TokenManager.load(tokenManagerAddress, web3j, credentials, new StaticGasProvider(GAS_PRICE, GAS_LIMIT));
	}
}
