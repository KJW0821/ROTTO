package com.rezero.rotto.api.service;

import com.rezero.rotto.dto.dto.SubscriptionListDto;
import com.rezero.rotto.dto.response.SubscriptionListResponse;
import com.rezero.rotto.entity.Subscription;
import com.rezero.rotto.entity.User;
import com.rezero.rotto.repository.SubscriptionRepository;
import com.rezero.rotto.repository.UserRepository;
import com.rezero.rotto.utils.Pagination;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class SubscriptionServiceImpl {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final Pagination pagination;

    public ResponseEntity<?> getSubscriptionList(int userCode, Integer page){
        User user = userRepository.findByUserCode(userCode);
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }

        List<Subscription> subscriptions = subscriptionRepository.findAll();
        List<SubscriptionListDto> subscriptionListDtos = new ArrayList<>();

        // 인덱스 선언
        int startIdx = 0;
        int endIdx = 0;
        // 총 페이지 수 선언
        int totalPages = 1;

        // 페이지네이션
        List<Integer> indexes = pagination.pagination(page, 10, subscriptions.size());
        startIdx = indexes.get(0);
        endIdx = indexes.get(1);
        totalPages = indexes.get(2);

        Collections.reverse(subscriptions);
        List<Subscription> pageSubscriptions = subscriptions.subList(startIdx, endIdx);

        for (Subscription subscription : pageSubscriptions) {

            SubscriptionListDto subscriptionListDto = SubscriptionListDto.builder()
                    .subscriptionCode(subscription.getSubscriptionCode())
                    .farmCode(subscription.getFarm().getFarmCode())
                    .farmName(subscription.getFarm().getFarmName())
                    .confirmPrice(subscription.getConfirmPrice())
                    .applyCount(subscription.getApplyCount())
                    .endTime(subscription.getEndedTime())
                    .limitNum(subscription.getLimitNum())
                    .build();

            subscriptionListDtos.add(subscriptionListDto);
        }

        SubscriptionListResponse response = SubscriptionListResponse.builder()
                .subscriptions(subscriptionListDtos)
                .totalPages(totalPages)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
