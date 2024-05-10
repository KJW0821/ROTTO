package com.rezero.rotto.api.service;

import com.rezero.rotto.dto.request.AccountCreateRequest;
import org.springframework.http.ResponseEntity;

public interface AccountService {

    // 공모계좌
    ResponseEntity<?> getAccountZero(int userCode);

    // 연결계좌
    ResponseEntity<?> getAccountOne(int userCode);

//    // 계좌생성
//    ResponseEntity<?> postAccountCreate(int userCode, AccountCreateRequest accountCreateRequest);
}
