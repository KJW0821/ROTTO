package com.rezero.rotto.api.service;

import org.springframework.http.ResponseEntity;

public interface TradeHistoryService {

    ResponseEntity<?> getTradeHistory(int userCode);
}
