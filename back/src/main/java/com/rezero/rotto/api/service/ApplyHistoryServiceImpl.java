package com.rezero.rotto.api.service;


import com.rezero.rotto.dto.dto.ApplyHistoryListCancelDto;
import com.rezero.rotto.dto.dto.ApplyHistoryListGetDto;
import com.rezero.rotto.dto.response.ApplyHistoryListCancelResponse;
import com.rezero.rotto.dto.response.ApplyHistoryListGetResponse;
import com.rezero.rotto.entity.ApplyHistory;
import com.rezero.rotto.entity.Farm;
import com.rezero.rotto.entity.Subscription;
import com.rezero.rotto.entity.User;
import com.rezero.rotto.repository.ApplyHistoryRepository;
import com.rezero.rotto.repository.FarmRepository;
import com.rezero.rotto.repository.SubscriptionRepository;
import com.rezero.rotto.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplyHistoryServiceImpl implements ApplyHistoryService{

    private final ApplyHistoryRepository applyHistoryRepository;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final FarmRepository farmRepository;


    @Override
    public ResponseEntity<?> postApply(int userCode, int subscriptionCode, int applyCount) {
        User user = userRepository.findByUserCode(userCode);
        LocalDateTime now = LocalDateTime.now();
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }

        ApplyHistory applyHistory = new ApplyHistory();
        Subscription subscription = subscriptionRepository.findBySubscriptionCode(subscriptionCode);

        int limitNum = subscription.getLimitNum();
        LocalDateTime startedTime = subscription.getStartedTime();
        LocalDateTime endedTime = subscription.getEndedTime();

        // 현재 시간이 시작 시간과 종료 시간 사이에 있는지 확인합니다.
        if (!now.isBefore(startedTime) && !now.isAfter(endedTime) && applyCount <= limitNum) {
            // 범위안에 있을 때
            applyHistory.setUserCode(userCode);
            applyHistory.setSubscriptionCode(subscriptionCode);
            applyHistory.setIsDelete(1);
            applyHistory.setApplyTime(now);
            applyHistory.setApplyCount(applyCount);
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

    @Override
    public ResponseEntity<?> getApply(int userCode) {
        User user = userRepository.findByUserCode(userCode);
        LocalDateTime now = LocalDateTime.now();
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }

        List<ApplyHistory> applyHistories = applyHistoryRepository.findByUserCodeAndIsDelete(userCode, 0);
        List<ApplyHistoryListGetDto> applyHistoryListDtos = new ArrayList<>();


        for (ApplyHistory applyHistory : applyHistories) {
            Subscription subscription = subscriptionRepository.findBySubscriptionCode(applyHistory.getSubscriptionCode());
            Farm farm = farmRepository.findByFarmCode(subscription.getFarmCode());
            ApplyHistoryListGetDto applyHistoryListDto = ApplyHistoryListGetDto.builder()
                    .applyHistoryCode(applyHistory.getApplyHistoryCode())
                    .subscriptionCode(applyHistory.getSubscriptionCode())
                    .userCode(applyHistory.getUserCode())
                    .farmCode(farm.getFarmCode())
                    .farmName(farm.getFarmName())
                    .confirmPrice(subscription.getConfirmPrice())
                    .applyTime(applyHistory.getApplyTime())
                    .startedTime(subscription.getStartedTime())
                    .endedTime(subscription.getEndedTime())
                    .build();

            applyHistoryListDtos.add(applyHistoryListDto);
        }

        ApplyHistoryListGetResponse response = ApplyHistoryListGetResponse.builder()
                .userApplyHistoryListGetDtos(applyHistoryListDtos)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<?> getApplyTerminated(int userCode) {
        User user = userRepository.findByUserCode(userCode);
        LocalDateTime now = LocalDateTime.now();
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }

        List<ApplyHistory> applyHistories = applyHistoryRepository.findByUserCodeAndIsDelete(userCode, 1);
        List<ApplyHistoryListCancelDto> applyHistoryListDtos = new ArrayList<>();


        for (ApplyHistory applyHistory : applyHistories) {
            Subscription subscription = subscriptionRepository.findBySubscriptionCode(applyHistory.getSubscriptionCode());
            Farm farm = farmRepository.findByFarmCode(subscription.getFarmCode());
            ApplyHistoryListCancelDto applyHistoryListDto = ApplyHistoryListCancelDto.builder()
                    .subscriptionCode(applyHistory.getSubscriptionCode())
                    .userCode(applyHistory.getUserCode())
                    .farmCode(farm.getFarmCode())
                    .farmName(farm.getFarmName())
                    .confirmPrice(subscription.getConfirmPrice())
                    .applyTime(applyHistory.getApplyTime())
                    .startedTime(subscription.getStartedTime())
                    .endedTime(subscription.getEndedTime())
                    .isDelete(applyHistory.getIsDelete())
                    .build();

            applyHistoryListDtos.add(applyHistoryListDto);
        }

        ApplyHistoryListCancelResponse response = ApplyHistoryListCancelResponse.builder()
                .userApplyHistoryListCancelDtos(applyHistoryListDtos)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
