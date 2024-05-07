package com.rezero.rotto.api.controller;

import com.rezero.rotto.api.service.FaqService;
import com.rezero.rotto.dto.response.FaqDetailResponse;
import com.rezero.rotto.dto.response.FaqListResponse;
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
@RequestMapping("/faq")
@RequiredArgsConstructor
@Tag(name = "FAQ 컨트롤러", description = "FAQ를 위한 API")
public class FaqController {

    private final FaqService faqService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "FAQ 목록 조회",
            description = "FAQ 전체 목록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "FAQ 조회 성공",
                    content = @Content(schema = @Schema(implementation = FaqListResponse.class))),
            @ApiResponse(responseCode = "404", description = "조회 실패")
    })

    @GetMapping
    public ResponseEntity<?> getFaqList(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @RequestParam(required = false) Integer page) {
        int userCode = Integer.parseInt(jwtTokenProvider.getPayload(authorizationHeader.substring(7)));
        return faqService.getFaqList(userCode, page);
    }


    @Operation(summary = "FAQ 상세 조회",
            description = "FAQ 상세 조회해볼까요?")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "FAQ 상세 조회 성공",
                    content = @Content(schema = @Schema(implementation = FaqDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 사용자")
    })

    @GetMapping("/{faq-code}")
    public ResponseEntity<?> getFaqDetail(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, int faqCode) {
        int userCode = Integer.parseInt(jwtTokenProvider.getPayload(authorizationHeader.substring(7)));
        return faqService.getFaqDetail(userCode, faqCode);
    }

}


