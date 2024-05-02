package com.rezero.rotto.api.service;

import com.rezero.rotto.dto.request.RegisterReqRequest;
import com.rezero.rotto.dto.response.ReqBoardDetailModifyResponse;
import org.springframework.http.ResponseEntity;

public interface ReqBoardService {

    ResponseEntity<?> getReqBoardList(int userCode);
    ResponseEntity<?> getReqBoardDetail(int userCode, int reqBoardCode);
    ResponseEntity<?> postReqBoard(int userCode , RegisterReqRequest reqRegisterBoard);
    ResponseEntity<?> updateReqBoard(int userCode, int reqBoardCode, ReqBoardDetailModifyResponse reqRegisterBoard);
    ResponseEntity<?> deleteReqBoard(int userCode, int reqBoardCode);
}
