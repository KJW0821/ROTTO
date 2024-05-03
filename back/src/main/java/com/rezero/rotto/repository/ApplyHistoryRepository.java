package com.rezero.rotto.repository;

import com.rezero.rotto.entity.ApplyHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyHistoryRepository extends JpaRepository<ApplyHistory, Integer> {

    // 유저가 신청한 청약리스트 조회
    ApplyHistory findByApplyHistoryCode(int applyHistoryCode);

    // 유저가 신청한 청약상세 조회
    ApplyHistory findByApplyHistoryCodeAndSubscriptionCode(int applyHistoryCode, int subscriptionCode);

}
