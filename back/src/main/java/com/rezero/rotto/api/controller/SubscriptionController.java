package com.rezero.rotto.api.controller;

import com.rezero.rotto.api.service.SubscriptionService;
import com.rezero.rotto.dto.request.SubscriptionProduceRequest;
import com.rezero.rotto.dto.response.SubscriptionDetailResponse;
import com.rezero.rotto.dto.response.SubscriptionListResponse;
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
@RequiredArgsConstructor
@RequestMapping("/subscription")
@Tag(name = "Subscription 컨트롤러", description = "청약조회를 위한 API")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "청약 목록 조회", description = "청약 목록을 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = SubscriptionListResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 사용자")
    })
    @GetMapping
    public ResponseEntity<?> getSubscriptionList(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                                 @RequestParam(name = "subs-status", required = false) Integer subsStatus,
                                                 @RequestParam(name = "min-price", required = false) Integer minPrice,
                                                 @RequestParam(name = "max-price", required = false) Integer maxPrice,
                                                 @RequestParam(name = "bean-type", required = false) String beanType,
                                                 @RequestParam(required = false) String sort,
                                                 @RequestParam(required = false) String keyword) {
        int userCode = Integer.parseInt(jwtTokenProvider.getPayload(authorizationHeader.substring(7)));
        return subscriptionService.getSubscriptionList(userCode, subsStatus, minPrice, maxPrice, beanType, sort, keyword);
    }


    @Operation(summary = "청약 상세 조회", description = "청약을 상세 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = SubscriptionDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 사용자")
    })
    @GetMapping("/{subscription-code}")
    public ResponseEntity<?> getSubscriptionDetail(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @PathVariable int subscriptionCode) {
        int userCode = Integer.parseInt(jwtTokenProvider.getPayload(authorizationHeader.substring(7)));
        return subscriptionService.getSubscriptionDetail(userCode, subscriptionCode);
    }


    @Operation(summary = "청약 정산")
    @PostMapping("/{subscription-code}")
    public ResponseEntity<?> calculateSubscription(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @PathVariable int subscriptionCode) {
        return subscriptionService.calculateSubscription(subscriptionCode);
    }

    @Operation(summary = "청약 생성", description = "청약생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "생성 성공"),
//                    content = @Content(schema = @Schema(implementation = SubscriptionDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 사용자")
    })
    @PostMapping
    public ResponseEntity<?> postSubscription(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @RequestBody SubscriptionProduceRequest subscriptionProduceRequest) {
        int userCode = Integer.parseInt(jwtTokenProvider.getPayload(authorizationHeader.substring(7)));
        return subscriptionService.postSubscription(userCode, subscriptionProduceRequest);

    }
}