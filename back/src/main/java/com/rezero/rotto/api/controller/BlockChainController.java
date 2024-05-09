package com.rezero.rotto.api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthSyncing;
import org.web3j.protocol.http.HttpService;

import com.rezero.rotto.api.service.BlockChainService;
import com.rezero.rotto.dto.request.CreateTokenRequest;
import com.rezero.rotto.dto.request.PayTokensRequest;
import com.rezero.rotto.dto.request.RefundsTokenRequest;
import com.rezero.rotto.entity.User;
import com.rezero.rotto.repository.UserRepository;
import com.rezero.rotto.utils.JwtTokenProvider;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/blocks")
@AllArgsConstructor
@Tag(name = "블록체인 컨트롤러", description = "스마트 컨트랙트에게 토큰(ROTTO) 관련 요청을 하기 위한 API")
public class BlockChainController {
	@Autowired
	private BlockChainService blockChainService;
	private UserRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;

	@Operation(summary = "ROTTO 생성", description = "입력받은 청약 code에 대한 ROTTO를 입력받은 수 만큼 생성한다.")
	@PostMapping("/create")
	public ResponseEntity<?> createToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
		@RequestBody CreateTokenRequest request) {
		try {
			int userCode = Integer.parseInt(jwtTokenProvider.getPayload(authorizationHeader.substring(7)));
			// userCode 활용해서 관리자인지 확인 필요
			User user = userRepository.findByUserCode(userCode);
			if(false)   return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("요청 권한이 없습니다.");

			return blockChainService.createToken(request);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("알 수 없는 에러가 발생하였습니다.");
		}
	}

	@Operation(summary = "ROTTO 지급", description = "")
	@PostMapping("/getToken")
	public ResponseEntity<?> getTokens(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @RequestBody
		PayTokensRequest request) {
		try{
			int userCode = Integer.parseInt(jwtTokenProvider.getPayload(authorizationHeader.substring(7)));
			// userCode 활용해서 관리자인지 확인 필요
			return blockChainService.distributeToken(request);
		} catch (Exception e){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("요청 권한이 없습니다.");
		}
	}

	@Operation(summary = "ROTTO 환급")
	@PostMapping("/burnToken")
	public ResponseEntity<?> RefundsToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
		@RequestBody RefundsTokenRequest request){
		try{
			int userCode = Integer.parseInt(jwtTokenProvider.getPayload(authorizationHeader.substring(7)));
			// userCode 활용해서 관리자인지 확인 필요
			return blockChainService.RefundsToken(request);
		} catch (Exception e){
			return ResponseEntity.badRequest().build();
		}
	}
}
