package com.rezero.rotto.api.controller;

import com.rezero.rotto.dto.dto.NewsListDto;
import com.rezero.rotto.dto.response.AlertListResponse;
import com.rezero.rotto.utils.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
@Tag(name = "News API", description = "서비스 관련 소식 관리를 위한 API")
public class NewsController {

    private final JwtTokenProvider jwtTokenProvider;


    @Operation(summary = "뉴스 목록 조회", description = "뉴스 목록을 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = NewsListDto.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 사용자")
    })
    @GetMapping
    public ResponseEntity<?> getNewsList(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        int userCode = Integer.parseInt(jwtTokenProvider.getPayload(authorizationHeader.substring(7)));
        List<NewsListDto> response = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @Operation(summary = "뉴스 상세 조회", description = "뉴스 상세 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = NewsListDto.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 사용자")
    })
    @GetMapping("/{newsCode}")
    public ResponseEntity<?> getNewsDetail(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        int userCode = Integer.parseInt(jwtTokenProvider.getPayload(authorizationHeader.substring(7)));
        List<NewsListDto> response = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
