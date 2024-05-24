package com.rezero.rotto.dto.request;

import lombok.Getter;

@Getter
public class SendMessageToRequest {

    private int userCode;
    private String topic;
    private String title;
    private String body;

}
