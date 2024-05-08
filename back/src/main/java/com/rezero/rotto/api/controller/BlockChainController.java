package com.rezero.rotto.api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthSyncing;
import org.web3j.protocol.http.HttpService;

import com.rezero.rotto.api.service.BlockChainService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/blocks")
@AllArgsConstructor
@Tag(name = "블록체인 컨트롤러", description = "스마트 컨트랙트에게 토큰(ROTTO) 관련 요청을 하기 위한 API")
public class BlockChainController {

	@Autowired
	BlockChainService blockChainService;

	@PostMapping("/create/{Subscription_code}")
	public ResponseEntity<?> createToken(@PathVariable int Subscription_code) {
		try {
			blockChainService.createToken(Subscription_code);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
}
