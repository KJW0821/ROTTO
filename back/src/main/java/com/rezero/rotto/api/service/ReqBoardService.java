package com.rezero.rotto.api.service;

import org.springframework.http.ResponseEntity;

public interface ReqBoardService {

    ResponseEntity<?> getReqBoardList(int userCode);

}
