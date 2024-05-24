package com.rezero.rotto.dto.request;

import lombok.Getter;

@Getter
public class SendMessageToAllUsersRequest {

    private String topic;
    private String title;
    private String body;

}
