package com.rezero.rotto.api.service;

import org.springframework.http.ResponseEntity;

public interface FarmService {

    ResponseEntity<?> getFarmList(int userCode, String sort, String keyword, Integer page);
    ResponseEntity<?> getFarmDetail(int userCode, int farmCode);
    ResponseEntity<?> getRateTop10FarmList(int userCode);

}
