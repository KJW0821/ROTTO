package com.rezero.rotto.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import com.rezero.rotto.entity.Subscription;
import com.rezero.rotto.repository.SubscriptionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlockChainServiceImpl implements BlockChainService{

	@Autowired
	private final SubscriptionRepository subscriptionRepository;


	@Override
	public void createToken(int subscriptionCode) throws Exception {
		Subscription subscription = subscriptionRepository.findBySubscriptionCode(subscriptionCode);
		if(subscription == null) {
			throw new Exception();
		}

		String rpcUrl = "";
		Web3j web3 = Web3j.build(new HttpService(rpcUrl));

		String contractAddress = "";
		String privateKey = ""; // 지갑 개인 키
		Credentials credentials = Credentials.create(privateKey);

		TransactionReceipt transactionReceipt;
	}
}
