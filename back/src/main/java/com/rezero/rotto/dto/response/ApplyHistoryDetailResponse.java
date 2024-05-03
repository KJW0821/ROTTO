package com.rezero.rotto.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
public class ApplyHistoryDetailResponse {

    private int applyHistoryCode;
    private int userCode;
    private int subscriptionCode;
    private int farmCode;
    private String farmName;
    private int confirmPrice;
    private LocalDateTime applyTime;
    private LocalDateTime startedTime;
    private LocalDateTime endTime;
    private int limitNum;
    private int returnRate;
    private int applyCount;

}
