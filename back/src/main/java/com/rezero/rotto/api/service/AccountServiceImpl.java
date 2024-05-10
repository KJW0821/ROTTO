package com.rezero.rotto.api.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.rezero.rotto.dto.request.AccountConnectionRequest;
import com.rezero.rotto.dto.request.CreateFinanceAccountRequest;
import com.rezero.rotto.dto.response.AccountConnectionResponse;
import com.rezero.rotto.dto.response.AccountOneResponse;
import com.rezero.rotto.dto.response.AccountZeroResponse;
import com.rezero.rotto.entity.Account;
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

    // rotto 계좌조회
    @Override
    public ResponseEntity<?> getAccountZero(int userCode) {
        User user = userRepository.findByUserCode(userCode);
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }

        // 공모계좌 가져오기
        Account accountZero = accountRepository.findByUserCodeAndAccountType(userCode, 0);
        // 얘를 뱃겨서 response에 담아야함.
        AccountZeroResponse accountZeroResponse = AccountZeroResponse.builder()
                .accountCode(accountZero.getAccountCode())
                .bankName(accountZero.getBankName())
                .accountHolder(user.getName())
                .accountNum(accountZero.getAccountNum())
                .accountBalance(accountZero.getBalance())
                .accountType(accountZero.getAccountType())
                .build();


        return ResponseEntity.status(HttpStatus.OK).body(accountZeroResponse);
    }

    @Override
    public ResponseEntity<?> getAccountOne(int userCode) {
        User user = userRepository.findByUserCode(userCode);
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }
        // 공모계좌 가져오기
        Account accountOne = accountRepository.findByUserCodeAndAccountType(userCode, 1);
        // 얘를 뱃겨서 response에 담아야함.
        AccountOneResponse accountOneResponse = AccountOneResponse.builder()
                .accountCode(accountOne.getAccountCode())
                .bankName(accountOne.getBankName())
                .accountHolder(user.getName())
                .accountNum(accountOne.getAccountNum())
                .accountBalance(accountOne.getBalance())
                .accountType(accountOne.getAccountType())
                .build();


        return ResponseEntity.status(HttpStatus.OK).body(accountOneResponse);
    }


    @Override
    public ResponseEntity<?> postAccountConnection(int userCode, AccountConnectionRequest accountConnectionRequest) {
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

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("apiName", "inquireAccountInfo");
        // 요청날짜
        headerMap.put("transmissionDate", now.format(dateFormatter));
        headerMap.put("transmissionTime", now.format(timeFormatter));
        // 기관코드 고정
        headerMap.put("institutionCode", "00100");
        //핀테크 앱 고정
        headerMap.put("fintechAppNo", "001");
        headerMap.put("apiServiceCode", "inquireAccountInfo");
        // 기관 거래 고유 번호 : 새로운 번호로 임의 채번 (YYYYMMDD + HHMMSS + 일련번호 6자리) 또는 20자리의 난수
        headerMap.put("institutionTransactionUniqueNo", institutionTransactionUniqueNo);
        // 개발자 키
        headerMap.put("apiKey", "2afacf41e60a4482b5c4997d194a46f0");
        // 계정생성해야함 -> 회원가입시 이메일을 작성하면 생성됨
        headerMap.put("userKey", user.getUserKey());


        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("Header", headerMap);
        // 은행코드(은행이름으로 불리는 코드)
        bodyMap.put("bankCode", accountConnectionRequest.getBankName());
        bodyMap.put("accountNo", accountConnectionRequest.getAccountNum());

        try {
            JsonNode jsonNode =  WebClient.create("https://finapi.p.ssafy.io")
                    .post()
                    .uri("/ssafy/api/v1/edu/account/inquireAccountInfo")
                    .bodyValue(bodyMap) // 구성한 Map을 bodyValue에 전달
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();

            System.out.println("금융망 계좌조회 호출결과: " + jsonNode);

            // 'REC' 필드에 접근
            JsonNode recNode = jsonNode.get("REC");

            if (recNode != null) { // 'REC' 필드가 존재하는지 확인
                String bankCode = recNode.get("bankCode").asText();
                String accountNo = recNode.get("accountNo").asText();
                Account account = new Account();
                account.setUserCode(userCode);
                account.setBankName(bankCode);
                account.setAccountNum(accountNo);
                account.setBalance(account.getBalance());
                account.setAccountType(1);
                accountRepository.save(account);


                AccountConnectionResponse accountConnectionResponse = AccountConnectionResponse.builder()
                        .accountCode(account.getAccountCode())
                        .accountNum(account.getAccountNum())
                        .bankName(account.getBankName())
                        .accountType(account.getAccountType())
                        .build();

                return ResponseEntity.status(HttpStatus.OK).body(accountConnectionResponse);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("계좌연결에 실패했어요");
    }
}
