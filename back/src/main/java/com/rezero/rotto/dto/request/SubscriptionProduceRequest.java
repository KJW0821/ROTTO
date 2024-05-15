package com.rezero.rotto.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionProduceRequest {
    private int farmCode;
    private int confirmPrice;
    private LocalDateTime startedTime;
    private LocalDateTime endedTime;
    private int limitNum;
    private int returnRate;
    private int partnerFarmRate;
    private int totalTokenCount;
}
