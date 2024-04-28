package com.rezero.rotto.api.controller;

import com.rezero.rotto.dto.response.TokenResponse;
import com.rezero.rotto.entity.BlackList;
import com.rezero.rotto.entity.User;
import com.rezero.rotto.repository.BlackListRepository;
import com.rezero.rotto.repository.RefreshTokenRepository;
import com.rezero.rotto.repository.UserRepository;
import com.rezero.rotto.utils.AESUtil;
import com.rezero.rotto.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final SecretKey aesKey;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BlackListRepository blackListRepository;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String phoneNum = loginData.get("phoneNum");
        String pin = loginData.get("pin");

        try {
            // 폰 번호 암호화
            String encryptedPhoneNum = AESUtil.encrypt(phoneNum, aesKey);

            // DB 에서 암호화된 폰 번호로 사용자 조회
            User user = userRepository.findByPhoneNum(encryptedPhoneNum)
                    .orElseThrow(() -> new RuntimeException("로그인에 실패하였습니다."));

            // PIN 해시값 검증
            if (!passwordEncoder.matches(pin, user.getPin())) {
                throw new RuntimeException("로그인에 실패하였습니다.");
            }
            
            // 액세스 토큰, 리프레시 토큰 발급
            String accessToken = jwtTokenProvider.createAccessToken(String.valueOf(user.getUserCode()));
            String refreshToken = jwtTokenProvider.createRefreshToken();

            TokenResponse tokenResponse = TokenResponse.builder()
                    .grantType("Bearer")
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();

            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            // 암호화 실패 또는 기타 예외 처리
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 도중 오류 발생.");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam("accessToken") String accessToken,
                                    @RequestParam("refreshToken") String refreshToken) {
        try {
            // 먼저 토큰의 유효성을 확인
            if (!jwtTokenProvider.validateToken(accessToken) || !jwtTokenProvider.validateToken(refreshToken)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효하지 않은 토큰입니다.");
            }

            // 토큰을 블랙리스트에 추가
            BlackList access = BlackList.builder()
                    .token(accessToken)
                    .expiration(jwtTokenProvider.getExpiration(accessToken))
                    .build();
            BlackList refresh = BlackList.builder()
                    .token(refreshToken)
                    .expiration(jwtTokenProvider.getExpiration(refreshToken))
                    .build();

            blackListRepository.save(access);
            blackListRepository.save(refresh);

            // 토큰을 저장소에서 삭제
            refreshTokenRepository.deleteById(refreshToken);

            return ResponseEntity.status(HttpStatus.OK).body("로그아웃 성공!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("로그아웃 처리 중 오류가 발생했습니다: " + e.getMessage());
        }
    }


}