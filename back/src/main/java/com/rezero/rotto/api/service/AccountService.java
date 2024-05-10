package com.rezero.rotto.api.service;

import com.rezero.rotto.dto.request.AccountConnectionRequest;
import org.springframework.http.ResponseEntity;

public interface AccountService {

    // 공모계좌 조회
    ResponseEntity<?> getAccountZero(int userCode);

    // 연결계좌 조회
    ResponseEntity<?> getAccountOne(int userCode);

    // 진짜계좌연결
    ResponseEntity<?> postAccountConnection(int userCode, AccountConnectionRequest accountConnectionRequest);
}
