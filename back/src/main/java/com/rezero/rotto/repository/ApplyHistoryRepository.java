package com.rezero.rotto.repository;

import com.rezero.rotto.entity.ApplyHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplyHistoryRepository extends JpaRepository<ApplyHistory, Integer> {

    ApplyHistory findByUserCodeAndSubscriptionCode(int userCode, int subscriptionCode);

    // 유저가 신청, 취소한 내역 조회
    List<ApplyHistory> findByUserCodeAndIsDelete(int userCode, int isDelete);

    Optional<List<ApplyHistory>> findBySubscriptionCodeAndIsDelete(int subscriptionCode, int isDelete);
}
