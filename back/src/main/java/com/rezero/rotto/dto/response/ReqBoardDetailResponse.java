package com.rezero.rotto.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ReqBoardDetailResponse {
    int reqBoardCode;
    String title;
    String contents;

}
