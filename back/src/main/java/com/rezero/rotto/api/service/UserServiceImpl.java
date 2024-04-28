package com.rezero.rotto.api.service;

import com.rezero.rotto.config.CryptoConfig;
import com.rezero.rotto.dto.request.SignUpRequest;
import com.rezero.rotto.dto.response.UserInfoResponse;
import com.rezero.rotto.entity.User;
import com.rezero.rotto.repository.BlackListRepository;
import com.rezero.rotto.repository.UserRepository;
import com.rezero.rotto.utils.AESUtil;
import com.rezero.rotto.utils.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final BlackListRepository blackListRepository;
    private final SecretKey aesKey;

    // 회원가입
    public ResponseEntity<?> signUp(SignUpRequest request) {
        try {
            String encryptedPhoneNum = AESUtil.encrypt(request.getPhoneNum(), aesKey);
            String encryptedJuminNo = AESUtil.encrypt(request.getJuminNo(), aesKey);
            String hashedPin = passwordEncoder.encode(request.getPin());

            // userCode 자동, isDelete 기본값 0, joinDate = CreationTimestamp, deleteTime = null
            User user = User.builder()
                .name(request.getName())
                .sex(request.getSex())
                .pin(hashedPin)
                .phoneNum(encryptedPhoneNum)
                .juminNo(encryptedJuminNo)
                .build();

            // 저장
            userRepository.save(user);

            // 리스폰스 생성
            UserInfoResponse response = UserInfoResponse.builder()
                    .userCode(user.getUserCode())
                    .name(user.getName())
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원가입 실패");
        }
    }



}
