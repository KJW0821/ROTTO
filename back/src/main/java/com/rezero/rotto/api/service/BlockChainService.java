package com.rezero.rotto.api.service;

import org.springframework.http.ResponseEntity;

import com.rezero.rotto.dto.request.CreateTokenRequest;
import com.rezero.rotto.dto.request.PayTokensRequest;
import com.rezero.rotto.dto.request.RefundsTokenRequest;

public interface BlockChainService {
	ResponseEntity<?> createToken(CreateTokenRequest request);

	ResponseEntity<?> distributeToken(PayTokensRequest request);

	ResponseEntity<?> RefundsToken(RefundsTokenRequest request);

	ResponseEntity<?> InsertWhiteList(String wallet);

	ResponseEntity<?> RemoveWhiteList(String wallet);
}
