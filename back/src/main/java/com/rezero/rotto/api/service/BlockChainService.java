package com.rezero.rotto.api.service;

import org.springframework.http.ResponseEntity;

import com.rezero.rotto.dto.request.CreateTokenRequest;
import com.rezero.rotto.dto.request.PayTokensRequest;
import com.rezero.rotto.dto.request.RefundsTokenRequest;

public interface BlockChainService {
	ResponseEntity<?> createToken(CreateTokenRequest request) throws Exception;

	ResponseEntity<?> distributeToken(PayTokensRequest request) throws Exception;

	ResponseEntity<?> RefundsToken(RefundsTokenRequest request) throws Exception;

	ResponseEntity<?> InsertWhiteList(String wallet) throws Exception;

	ResponseEntity<?> RemoveWhiteList(String wallet) throws Exception;
}
