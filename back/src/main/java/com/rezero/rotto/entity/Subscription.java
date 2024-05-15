package com.rezero.rotto.entity;

import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.checker.units.qual.C;

import java.time.LocalDateTime;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subscription_tb")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_code")
    private int subscriptionCode;

    @Column(name = "farm_code")
    private int farmCode;

    @Column(name = "confirm_price")
    private int confirmPrice;

    @Column(name = "started_time")
    private LocalDateTime startedTime;

    @Column(name = "ended_time")
    private LocalDateTime endedTime;

    @Column(name = "limit_num")
    private int limitNum;

    @Column(name = "return_rate")
    private int returnRate;

    @Column(name = "total_token_count")
    private int totalTokenCount;

    @Column(name = "partner_farm_rate")
    private int partnerFarmRate;
}
