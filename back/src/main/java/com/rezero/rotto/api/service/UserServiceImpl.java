package com.rezero.rotto.api.service;

//import com.rezero.rotto.dto.request.RegisterPinRequest;
import com.rezero.rotto.dto.request.SignUpRequest;
import com.rezero.rotto.dto.response.UserInfoResponse;
import com.rezero.rotto.entity.User;
import com.rezero.rotto.repository.UserRepository;
import com.rezero.rotto.utils.AESUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecretKey aesKey;


    // 회원가입
    public ResponseEntity<?> signUp(SignUpRequest request) {
        try {
            String encryptedPhoneNum = AESUtil.encrypt(request.getPhoneNum(), aesKey);
            String encryptedJuminNo = AESUtil.encrypt(request.getJuminNo(), aesKey);
            String hashedPassword = passwordEncoder.encode(request.getPassword());

            // 이미 존재하는 휴대폰 번호로 가입을 시도할 경우 예외 처리
            if (userRepository.existsByPhoneNum(encryptedPhoneNum)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 휴대폰 번호입니다.");
            }
            
            // userCode 자동, isDelete 기본값 0, joinTime = CreationTimestamp, deleteTime = null
            User user = User.builder()
                    .name(request.getName())
                    .sex(request.getSex())
                    .phoneNum(encryptedPhoneNum)
                    .juminNo(encryptedJuminNo)
                    .password(hashedPassword)
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
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원가입 실패");
        }
    }

//
//    // PIN 번호 등록
//    public ResponseEntity<?> registerPin(int userCode, RegisterPinRequest request) {
//        // 유저가 존재하는지 검사
//        User user = userRepository.findByUserCode(userCode);
//        if (user == null || user.getIsDelete()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
//        }
//        String hashedPin = passwordEncoder.encode(request.getPin());
//
//        // PIN 번호 변경
//        user.setPassword(hashedPin);
//        userRepository.save(user);
//
//        return ResponseEntity.status(HttpStatus.OK).body("PIN 번호 등록 성공!");
//    }


    // 사용자 정보 조회
    public ResponseEntity<?> getUserInfo(int userCode) {
        // 해당 유저가 존재하는지 검사
        User user = userRepository.findByUserCode(userCode);
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }

        UserInfoResponse response = UserInfoResponse.builder()
                .userCode(user.getUserCode())
                .name(user.getName())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    // 회원 탈퇴
    public ResponseEntity<String> deleteUser(int userCode) {
        // 해당 유저가 존재하는지 검사
        User user = userRepository.findByUserCode(userCode);
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }

        // isDelete = true, deleteTime = 현재 시간 으로 수정 후 저장
        user.setIsDelete(true);
        user.setDeleteTime(LocalDateTime.now());
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body("탈퇴 성공");
    }
}
