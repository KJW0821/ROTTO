package com.rezero.rotto.api.service;

import org.springframework.http.ResponseEntity;

public interface AccountHistoryService {
    ResponseEntity<?> getAccountHistory(int userCode, int accountCode);
}
