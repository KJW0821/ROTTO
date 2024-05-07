package com.rezero.rotto.api.service;


import com.rezero.rotto.entity.ApplyHistory;
import com.rezero.rotto.entity.Subscription;
import com.rezero.rotto.entity.User;
import com.rezero.rotto.repository.ApplyHistoryRepository;
import com.rezero.rotto.repository.SubscriptionRepository;
import com.rezero.rotto.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplyHistoryServiceImpl implements ApplyHistoryService{

    private final ApplyHistoryRepository applyHistoryRepository;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;


    @Override
    public ResponseEntity<?> postApply(int userCode, int subscriptCode) {
        User user = userRepository.findByUserCode(userCode);
        LocalDateTime now = LocalDateTime.now();
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }

        ApplyHistory applyHistory = new ApplyHistory();
        Subscription subscription = subscriptionRepository.findBySubscriptionCode(subscriptCode);

        LocalDateTime startedTime = subscription.getStartedTime();
        LocalDateTime endedTime = subscription.getEndedTime();

        // 현재 시간이 시작 시간과 종료 시간 사이에 있는지 확인합니다.
        if (!now.isBefore(startedTime) && !now.isAfter(endedTime)) {
            // 범위안에 있을 때
            applyHistory.setUserCode(userCode);
            applyHistory.setSubscriptionCode(subscriptCode);
            applyHistory.setIsDelete(1);
            applyHistory.setApplyTime(now);
            applyHistoryRepository.save(applyHistory);

            return ResponseEntity.status(HttpStatus.OK).body(applyHistory);
        }


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("신청기간이 아닙니다.");



    }

    @Override
    public ResponseEntity<?> deleteApply(int userCode, int subscriptionCode) {
        User user = userRepository.findByUserCode(userCode);
        LocalDateTime now = LocalDateTime.now();
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }

        ApplyHistory applyHistory = applyHistoryRepository.findByUserCodeAndSubscriptionCode(userCode, subscriptionCode);
        Subscription subscription = subscriptionRepository.findBySubscriptionCode(applyHistory.getSubscriptionCode());

        LocalDateTime startedTime = subscription.getStartedTime();
        LocalDateTime endedTime = subscription.getEndedTime();

        // 현재 시간이 시작 시간과 종료 시간 사이에 있는지 확인합니다.
        if (!now.isBefore(startedTime) && !now.isAfter(endedTime)) {
            // 범위안에 있을 때
            applyHistory.setIsDelete(0);
            applyHistoryRepository.save(applyHistory);

            return ResponseEntity.status(HttpStatus.OK).body(applyHistory);
        }


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("신청기간이 아닙니다.");

    }

}
