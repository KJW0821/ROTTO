package com.rezero.rotto.api.service;

import org.springframework.http.ResponseEntity;

public interface ApplyHistoryService {

    // 청약신청
    ResponseEntity<?> postApply(int useCode, int subscriptCode);

    // 청약 신청 취소
    ResponseEntity<?> deleteApply(int userCode, int applyHistoryCode);

    // 유저의 청약신청 리스트
    ResponseEntity<?> getApplyHistoryListByUser(int userCode, Integer page);

    // 유저의 청얀식청 리스트 상세
    ResponseEntity<?> getApplyDetailByUser(int userCode, int subscriptionCode);

}
