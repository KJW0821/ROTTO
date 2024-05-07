package com.rezero.rotto.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "apply_history_tb")
public class ApplyHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apply_history_code")
    int applyHistoryCode;

    @Column(name = "user_code")
    private int userCode;

    @Column(name = "subscription_code")
    private int subscriptionCode;

    @Column(name = "apply_time")
    private LocalDateTime applyTime;

    @Column(name = "is_delete", columnDefinition = "TINYINT(1) DEFAULT 0")
    int isDelete;

    public void setApplyTime() {this.applyTime = LocalDateTime.now();}
}
