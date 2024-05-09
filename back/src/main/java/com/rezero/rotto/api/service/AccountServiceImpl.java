package com.rezero.rotto.api.service;

import com.rezero.rotto.dto.request.AccountCreateRequest;
import com.rezero.rotto.entity.User;
import com.rezero.rotto.repository.AccountRepository;
import com.rezero.rotto.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService{

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    @Override
    public ResponseEntity<?> getAccountZero(int userCode) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAccountOne(int userCode) {
        return null;
    }

    @Override
    public ResponseEntity<?> postAccountCreate(int userCode, AccountCreateRequest accountCreateRequest) {
        // 해당 유저가 존재하는지 검사
        User user = userRepository.findByUserCode(userCode);
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");

        // 날짜와 시간 포맷
        String formattedDate = now.format(dateFormatter);
        String formattedTime = now.format(timeFormatter);

        // 랜덤 6자리 일련번호 생성
        Random random = new Random();
        int randomNumber = random.nextInt(999999); // 0에서 999999까지의 난수 생성
        String formattedRandomNumber = String.format("%06d", randomNumber); // 난수를 6자리 문자열로 포맷팅

        // 기관 거래 고유 번호 생성
        String institutionTransactionUniqueNo = formattedDate + formattedTime + formattedRandomNumber;

        // 요청 본문에 포함할 JSON 데이터 구성
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("apiName", "openAccount");
        // 요청날짜
        headerMap.put("transmissionDate", now.format(dateFormatter));
        headerMap.put("transmissionTime", now.format(timeFormatter));
        // 기관코드 고정
        headerMap.put("institutionCode", "00100");
        //핀테크 앱 고정
        headerMap.put("fintechAppNo", "001");
        headerMap.put("apiServiceCode", "openAccount");
        // 기관 거래 고유 번호 : 새로운 번호로 임의 채번 (YYYYMMDD + HHMMSS + 일련번호 6자리) 또는 20자리의 난수
        headerMap.put("institutionTransactionUniqueNo", institutionTransactionUniqueNo);
        // 개발자 키
        headerMap.put("apiKey", "2afacf41e60a4482b5c4997d194a46f0");
        // 계정생성해야함 -> 회원가입시 이메일을 작성하면 생성됨.(현재는 예시)
        headerMap.put("userKey", "2c357240-6bb3-484a-bd43-f2b9674263be");
/**
 {
 "code": "succeed",
 "payload": {
 "userId": "sehun9803@naver.com",
 "userName": "sehun9803",
 "institutionCode": "00100",
 "userKey": "2c357240-6bb3-484a-bd43-f2b9674263be",
 "created": "2024-05-08T17:39:28.892395+09:00",
 "modified": "2024-05-08T17:39:28.892393+09:00"
 },
 "now": "2024-05-08T17:39:28.928175+09:00"
 }
 **/

        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("Header", headerMap);
        // 상품 고유번호
        bodyMap.put("accountTypeUniqueNo", "001-1-81fe2deafd1943"); // 한국은행 입출금 상품

        try {
            String apiResponse =  WebClient.create("https://finapi.p.ssafy.io/ssafy/api/v1/")
                    .post()
                    .uri("edu/account/openAccount")
                    .bodyValue(bodyMap) // 구성한 Map을 bodyValue에 전달
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            System.out.println("API 호출 결과: " + apiResponse);



            // 요청이 성공적으로 완료된 후의 처리를 여기에 작성
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (Exception e) {
            // 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("계좌 생성 요청 중 오류가 발생했습니다.");
        }
    }

}
