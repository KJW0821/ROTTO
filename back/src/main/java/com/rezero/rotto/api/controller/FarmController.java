package com.rezero.rotto.api.controller;

import com.rezero.rotto.api.service.FarmService;
import com.rezero.rotto.dto.response.NoticeDetailResponse;
import com.rezero.rotto.dto.response.NoticeListResponse;
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
@RequestMapping("/farm")
@RequiredArgsConstructor
@Tag(name = "Farm 컨트롤러", description = "농장 관리를 위한 API")
public class FarmController {

    private final JwtTokenProvider jwtTokenProvider;
    private final FarmService farmService;

    @Operation(summary = "농장 목록 조회", description = "필터링, 정렬, 검색, 페이지네이션을 포함한 농장 목록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = NoticeListResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 사용자")
    })
    @GetMapping
    public ResponseEntity<?> getNoticeList(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                           @RequestParam(required = false) String sort,
                                           @RequestParam(required = false) String keyword,
                                           @RequestParam(required = false) Integer page) {
        int userCode = Integer.parseInt(jwtTokenProvider.getPayload(authorizationHeader.substring(7)));
        return farmService.getFarmList(userCode, sort, keyword, page);
    }


    @Operation(summary = "농장 상세 조회", description = "농장 상세 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = NoticeDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "사용자 혹은 농장이 존재하지 않음")
    })
    @GetMapping("/{farmCode}")
    public ResponseEntity<?> getFarmDetail(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @PathVariable int farmCode) {
        int userCode = Integer.parseInt(jwtTokenProvider.getPayload(authorizationHeader.substring(7)));
        return farmService.getFarmDetail(userCode, farmCode);
    }
}
