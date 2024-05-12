package com.rezero.rotto.repository;

import com.rezero.rotto.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer>, JpaSpecificationExecutor<Subscription> {

    // 청약목록상세조회
    Subscription findBySubscriptionCode(int subscriptionCode);

    List<Subscription> findByFarmCode(int farmCode);


}
