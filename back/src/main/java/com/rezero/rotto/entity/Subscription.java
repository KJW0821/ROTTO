package com.rezero.rotto.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
    int subscriptionCode;

    @Column(name = "farm_code")
    private int farmCode;

    @Column(name = "confirm_price")
    int confirmPrice;

    @Column(name = "started_time")
    LocalDateTime startedTime;

    @Column(name = "end_time")
    LocalDateTime endedTime;

    @Column(name = "limit_num")
    int limitNum;

    @Column(name = "return_rate")
    int returnRate;

    @Column(name = "apply_count")
    int applyCount;


}
