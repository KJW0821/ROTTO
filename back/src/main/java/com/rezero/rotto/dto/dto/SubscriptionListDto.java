package com.rezero.rotto.dto.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class SubscriptionListDto {

    private int subscriptionCode;
    private int farmCode;
    private int confirmPrice;
    private LocalDateTime startedTime;
    private LocalDateTime endTime;
    private int limitNum;
    private int returnRate;
    private int applyCount;

}
