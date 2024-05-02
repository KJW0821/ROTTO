package com.rezero.rotto.repository;

import com.rezero.rotto.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    // 청약목록상세조회
    Subscription findBySubscriptionCode(int subscriptionCode);


}
