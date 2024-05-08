package com.rezero.rotto.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import com.rezero.rotto.contracts.TokenCreation;
import com.rezero.rotto.contracts.TokenDeletion;
import com.rezero.rotto.contracts.TokenDistribute;
import com.rezero.rotto.contracts.TokenManager;
import com.rezero.rotto.contracts.TokenStorage;

@Configuration
public class Web3jConfig {

	@Value("${blockchain.rpc.url}")
	private String rpcUrl;

	@Value("${blockchain.private-key}")
	private String privateKey;
	@Bean
	public Web3j web3j() {
		return Web3j.build(new HttpService(rpcUrl));
	}
	@Bean
	public Credentials credentials() {
		return Credentials.create(privateKey);
	}
}
