package com.rezero.rotto.api.controller;

import com.rezero.rotto.api.service.FirebaseService;
//import com.rezero.rotto.config.FirebaseConfig;
import com.rezero.rotto.dto.request.FirebaseNotificationRequest;
import com.rezero.rotto.dto.response.FarmListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/firebase")
@Tag(name = "Firebase 컨트롤러", description = "푸시 알림 관리를 위한 API")
public class FirebaseController {

    private final FirebaseService firebaseService;


    @Operation(summary = "요청에 대한 알림 전송", description = "해당 요청이 들어올 때 알림 전송")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 사용자")
    })
    @GetMapping
    public ResponseEntity<?> sendNotification(@RequestBody FirebaseNotificationRequest request) {
        firebaseService.sendMessage(request.getTopic(), request.getTitle(), request.getBody());
        return ResponseEntity.ok().build();
    }

}
