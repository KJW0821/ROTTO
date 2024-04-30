package com.rezero.rotto.api.service;

import org.springframework.http.ResponseEntity;

public interface NoticeService {

    ResponseEntity<?> getNoticeList(int userCode, Integer page);
}
