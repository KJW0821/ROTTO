package com.rezero.rotto.api.controller;

import com.rezero.rotto.api.service.AccountService;
import com.rezero.rotto.dto.request.AccountConnectionRequest;
import com.rezero.rotto.dto.response.AccountConnectionResponse;
import com.rezero.rotto.dto.response.AccountOneResponse;
import com.rezero.rotto.dto.response.AccountZeroResponse;
import com.rezero.rotto.utils.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Tag(name = "Account 컨트롤러", description = "계좌관련 Api")
public class AccountController {

    private final AccountService accountService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "rotto 계좌 조회",
            description = "내 로또계좌 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode ="200", description = "rotto 계좌조회 성공",
                    content = @Content(schema = @Schema(implementation = AccountZeroResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 요청")
    })

    @GetMapping
    public ResponseEntity<?> getAccountZero(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        int userCode = Integer.parseInt(jwtTokenProvider.getPayload(authorizationHeader.substring(7)));
        return accountService.getAccountZero(userCode);
    }

    @Operation(summary = "진짜 계좌 연결",
            description = "진짜 내 계좌 연결")
    @ApiResponses(value = {
            @ApiResponse(responseCode ="200", description = "진짜 내 계좌 연결성공",
                    content = @Content(schema = @Schema(implementation = AccountConnectionResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 요청")
    })

    @PostMapping("/connection")
    public ResponseEntity<?> postAccountConnection(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, AccountConnectionRequest accountConnectionRequest){
        int userCode = Integer.parseInt(jwtTokenProvider.getPayload(authorizationHeader.substring(7)));
        return accountService.postAccountConnection(userCode, accountConnectionRequest);
    }

}
