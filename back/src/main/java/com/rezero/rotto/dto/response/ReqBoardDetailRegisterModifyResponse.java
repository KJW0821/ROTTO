package com.rezero.rotto.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ReqBoardDetailRegisterModifyResponse {
    private int reqBoardCode;
    private String user;
    private String title;
    private String contents;

}
