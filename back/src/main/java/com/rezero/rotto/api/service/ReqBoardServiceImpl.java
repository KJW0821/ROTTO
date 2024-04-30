package com.rezero.rotto.api.service;

import com.rezero.rotto.dto.dto.ReqBoardListDto;
import com.rezero.rotto.dto.request.RegisterReqRequest;
import com.rezero.rotto.dto.response.ReqBoardDetailRegisterModifyResponse;
import com.rezero.rotto.dto.response.ReqBoardListResponse;
import com.rezero.rotto.entity.ReqBoard;
import com.rezero.rotto.entity.User;
import com.rezero.rotto.repository.ReqBoardRepository;
import com.rezero.rotto.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReqBoardServiceImpl implements ReqBoardService {

    private final ReqBoardRepository reqBoardRepository;
    private final UserRepository userRepository;

    public ResponseEntity<?> getReqBoardList(int userCode) {
        User user = userRepository.findByUserCode(userCode);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        List<ReqBoard> reqBoardList = reqBoardRepository.findAll();
        List<ReqBoardListDto> reqBoardListDtos = new ArrayList<>();

        for (int i = 0; i < reqBoardList.size(); i++) {
            ReqBoardListDto reqBoardListDto = ReqBoardListDto.builder()
                    .reqBoardCode(reqBoardList.get(i).getReqBoardCode())
                    .title(reqBoardList.get(i).getTitle())
                    .build();
            reqBoardListDtos.add(reqBoardListDto);

        }

        ReqBoardListResponse reqBoardListResponse = ReqBoardListResponse.builder()
                .reqBoardListDtos(reqBoardListDtos)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(reqBoardListResponse);
    }

    public ResponseEntity<?> getReqBoardDetail(int userCode, int reqBoardCode) {
        User user = userRepository.findByUserCode(userCode);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        ReqBoard reqBoardDetail = reqBoardRepository.findByReqBoardCode(reqBoardCode);
        ReqBoardDetailRegisterModifyResponse reqBoardDetailResponse = ReqBoardDetailRegisterModifyResponse.builder()
                .reqBoardCode(reqBoardDetail.getReqBoardCode())
                .title(reqBoardDetail.getTitle())
                .contents(reqBoardDetail.getContent())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(reqBoardDetailResponse);
    }

    @Override
    public ResponseEntity<?> postReqBoard(int userCode, ReqBoardDetailRegisterModifyResponse reqRegisterBoard) {
        // 해당 유저가 존재하는지 검사
        User user = userRepository.findByUserCode(userCode);
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }

        // ReqBoard 엔티티 생성 및 저장
        ReqBoard reqBoard = new ReqBoard();
        reqBoard.setTitle(reqRegisterBoard.getTitle());
        reqBoard.setContent(reqRegisterBoard.getContents());
        reqBoard.setUserCode(userCode); // User 엔티티 설정
        reqBoardRepository.save(reqBoard);

        return ResponseEntity.status(HttpStatus.CREATED).body("게시글 생성 완료");
    }

}

