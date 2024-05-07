package com.rezero.rotto.api.service;

import com.rezero.rotto.dto.request.FarmListRequest;
import org.springframework.http.ResponseEntity;

public interface FarmService {

    ResponseEntity<?> getFarmList(int userCode, String sort, String keyword, FarmListRequest request);
    ResponseEntity<?> getFarmDetail(int userCode, int farmCode);
    ResponseEntity<?> getRateTop10FarmList(int userCode);

}
