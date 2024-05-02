package com.rezero.rotto.api.controller;

import com.rezero.rotto.api.service.AlertService;
import com.rezero.rotto.dto.response.AlertDetailResponse;
import com.rezero.rotto.dto.response.AlertListResponse;
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
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "Alert 컨트롤러", description = "알림 관리를 위한 API")
public class AlertController {

    private final AlertService alertService;
    private final JwtTokenProvider jwtTokenProvider;


    @Operation(summary = "알림 목록 조회", description = "알림 목록을 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = AlertListResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 사용자")
    })
    @GetMapping
    public ResponseEntity<?> getAlertList(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        int userCode = Integer.parseInt(jwtTokenProvider.getPayload(authorizationHeader.substring(7)));
        return alertService.getAlertList(userCode);
    }


    @Operation(summary = "알림 상세 조회", description = "알림 상세 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = AlertDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "사용자 혹은 알림이 존재하지 않음")
    })
    @GetMapping("/{alertCode}")
    public ResponseEntity<?> getAlertDetail(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                            @PathVariable int alertCode) {
        int userCode = Integer.parseInt(jwtTokenProvider.getPayload(authorizationHeader.substring(7)));
        return alertService.getAlertDetail(userCode, alertCode);
    }


    @Operation(summary = "알림 삭제", description = "알림 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "사용자 혹은 알림이 존재하지 않음")
    })
    @DeleteMapping("/{alertCode}")
    public ResponseEntity<?> deleteAlert(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                            @PathVariable int alertCode) {
        int userCode = Integer.parseInt(jwtTokenProvider.getPayload(authorizationHeader.substring(7)));
        return alertService.deleteAlert(userCode, alertCode);
    }


    @Operation(summary = "알림 모두 읽음 처리", description = "알림 모두 읽음 처리")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "사용자가 존재하지 않음")
    })
    @PutMapping("/all-read")
    public ResponseEntity<?> readAllAlert(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        int userCode = Integer.parseInt(jwtTokenProvider.getPayload(authorizationHeader.substring(7)));
        return alertService.readAllAlert(userCode);
    }

}
