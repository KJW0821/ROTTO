package com.rezero.rotto.api.controller;

import com.rezero.rotto.api.service.UserService;
import com.rezero.rotto.dto.request.CheckPhoneNumRequest;
import com.rezero.rotto.dto.request.SignUpRequest;
import com.rezero.rotto.dto.response.CheckPhoneNumResponse;
import com.rezero.rotto.dto.response.UserInfoResponse;
import com.rezero.rotto.utils.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "User 컨트롤러", description = "사용자 관리를 위한 API")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "회원가입", description = "유저 정보를 받아 회원가입")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원가입 성공",
                    content = @Content(schema = @Schema(implementation = UserInfoResponse.class))),
            @ApiResponse(responseCode = "400", description = "데이터가 잘못된 입력됨"),
            @ApiResponse(responseCode = "409", description = "이미 존재하는 휴대폰 번호로 회원가입을 시도하여 실패")
    })
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest request) {
        return userService.signUp(request);
    }


    @Operation(summary = "휴대폰 번호 중복 체크", description = "휴대폰 번호를 중복 체크하여 Boolean 타입으로 반환")
    @ApiResponse(responseCode = "200", description = "중복 체크 요청 성공",
            content = @Content(schema = @Schema(implementation = CheckPhoneNumResponse.class)))
    @PostMapping("/phone-num-check")
    public ResponseEntity<?> checkPhoneNum(@Valid @RequestBody CheckPhoneNumRequest request) {
        return userService.checkPhoneNum(request);
    }


    @Operation(summary = "사용자 정보 조회", description = "사용자 정보를 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = UserInfoResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 사용자")
    })
    @GetMapping
    public ResponseEntity<?> getUserInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        int userCode = Integer.parseInt(jwtTokenProvider.getPayload(authorizationHeader.substring(7)));
        return userService.getUserInfo(userCode);
    }


    @Operation(summary = "회원 탈퇴", description = "Soft Delete 를 사용해서 회원 탈퇴")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 사용자")
    })
    @PatchMapping
    public ResponseEntity<?> deleteUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        int userCode = Integer.parseInt(jwtTokenProvider.getPayload(authorizationHeader.substring(7)));
        return userService.deleteUser(userCode);
    }

}
