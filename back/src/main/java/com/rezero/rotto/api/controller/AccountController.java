package com.rezero.rotto.api.controller;

import com.rezero.rotto.api.service.AccountService;
import com.rezero.rotto.dto.request.AccountCreateRequest;
import com.rezero.rotto.dto.response.ApplyHistoryResponse;
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

    @Operation(summary = "계좌생성",
            description = "금융망에 계좌를 생성해봅시다.(rotto용 계좌)")
    @ApiResponses(value = {
            @ApiResponse(responseCode ="200", description = "계좌생성 성공",
                    content = @Content(schema = @Schema(implementation = ApplyHistoryResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 요청")
    })

    // 받을값들
    @PostMapping("/create")
    public ResponseEntity<?> postAccountCreate(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, AccountCreateRequest accountCreateRequest){
        int userCode = Integer.parseInt(jwtTokenProvider.getPayload(authorizationHeader.substring(7)));
        return accountService.postAccountCreate(userCode, accountCreateRequest);
    }

}
