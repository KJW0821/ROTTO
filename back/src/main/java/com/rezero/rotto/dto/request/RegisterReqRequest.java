package com.rezero.rotto.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class RegisterReqRequest {

    private String user;
    private String title;
    private String content;

}
