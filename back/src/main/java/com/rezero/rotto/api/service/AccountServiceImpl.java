package com.rezero.rotto.api.service;

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
        return null;
    }


}
