package com.rezero.rotto.api.controller;

import com.rezero.rotto.api.service.AccountHistoryService;
import com.rezero.rotto.dto.response.AccountHistoryListResponse;
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
@RequestMapping("/account-history")
@RequiredArgsConstructor
@Tag(name = "AccountHistory 컨트롤러", description = "공모(rotto)계좌의 입출금 내역 조회를 위한 API")
public class AccountHistoryController {

    private final AccountHistoryService accountHistoryService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "입출금내역 조회",
            description = "가상계좌의 입출금내역 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode ="200", description = "입출금내역 조회 성공",
                    content = @Content(schema = @Schema(implementation = AccountHistoryListResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않은 청약")
    })

    // 받을값들
    @GetMapping("/{accountCode}")
    public ResponseEntity<?> getAccountHistory(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @PathVariable int accountCode){
        int userCode = Integer.parseInt(jwtTokenProvider.getPayload(authorizationHeader.substring(7)));
        return accountHistoryService.getAccountHistory(userCode, accountCode);
    }
}
