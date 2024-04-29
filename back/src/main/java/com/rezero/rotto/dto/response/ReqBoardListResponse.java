package com.rezero.rotto.dto.response;

import com.rezero.rotto.dto.dto.ReqBoardListDto;
import lombok.Builder;

import java.util.List;

@Builder
public class ReqBoardListResponse {

    List<ReqBoardListDto> reqBoardListDtos;

}
