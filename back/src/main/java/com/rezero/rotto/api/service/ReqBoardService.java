package com.rezero.rotto.api.service;

import com.rezero.rotto.dto.response.ReqBoardDetailRegisterModifyResponse;
import org.springframework.http.ResponseEntity;

public interface ReqBoardService {

    ResponseEntity<?> getReqBoardList(int userCode);
    ResponseEntity<?> getReqBoardDetail(int userCode, int reqBoardCode);
    ResponseEntity<?> postReqBoard(int userCode , ReqBoardDetailRegisterModifyResponse reqRegisterBoard);
}
