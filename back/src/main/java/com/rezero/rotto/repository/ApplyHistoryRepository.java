package com.rezero.rotto.repository;

import com.rezero.rotto.entity.ApplyHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyHistoryRepository extends JpaRepository<ApplyHistory, Integer> {

    ApplyHistory findByUserCodeAndSubscriptionCode(int userCode, int subscriptionCode);
}
