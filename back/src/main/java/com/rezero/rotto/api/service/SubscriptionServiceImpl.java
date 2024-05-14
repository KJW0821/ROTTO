package com.rezero.rotto.api.service;

import com.rezero.rotto.dto.dto.SubscriptionListDto;
import com.rezero.rotto.dto.response.SubscriptionDetailResponse;
import com.rezero.rotto.dto.response.SubscriptionListResponse;
import com.rezero.rotto.entity.ApplyHistory;
import com.rezero.rotto.entity.Farm;
import com.rezero.rotto.entity.Subscription;
import com.rezero.rotto.entity.User;
import com.rezero.rotto.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import static com.rezero.rotto.utils.Const.VALID_BEAN_TYPES;
@Service
@RequiredArgsConstructor
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService{

    private final SubscriptionRepository subscriptionRepository;
    private final ApplyHistoryRepository applyHistoryRepository;
    private final UserRepository userRepository;
    private final FarmRepository farmRepository;


    public ResponseEntity<?> getSubscriptionList(int userCode, Integer subsStatus, Integer minPrice, Integer maxPrice, String beanType, String sort, String keyword){
        User user = userRepository.findByUserCode(userCode);
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }

        // 입력값 유효성 검사
        if (!isValidInput(subsStatus, minPrice, maxPrice, beanType)) {
            return ResponseEntity.badRequest().body("잘못된 입력값입니다.");
        }

        Specification<Subscription> spec = Specification.where(null);
        if (keyword != null) spec = spec.and(SubscriptionSpecification.nameContains(keyword));
        if (subsStatus != null) spec = spec.and(SubscriptionSpecification.filterBySubscriptionStatus(subsStatus));
        if (minPrice != null || maxPrice != null) spec = spec.and(SubscriptionSpecification.priceBetween(minPrice, maxPrice));
        if (beanType != null ) spec = spec.and(SubscriptionSpecification.filterByBeanType(beanType));
        spec = spec.and(SubscriptionSpecification.applySorting(sort));

        List<Subscription> subscriptions = subscriptionRepository.findAll(spec);
        List<SubscriptionListDto> subscriptionListDtos = new ArrayList<>();

        for (Subscription subscription : subscriptions) {
            Farm farm = farmRepository.findByFarmCode(subscription.getFarmCode());
            int subscriptionCode = subscription.getSubscriptionCode();
            Integer applyCount = applyHistoryRepository.sumApplyCountBySubscriptionCode(subscriptionCode);
            applyCount = (applyCount != null) ? applyCount : 0;
            SubscriptionListDto subscriptionListDto = SubscriptionListDto.builder()
                    .subscriptionCode(subscriptionCode)
                    .farmCode(farm.getFarmCode())
                    .farmName(farm.getFarmName())
                    .confirmPrice(subscription.getConfirmPrice())
                    .applyCount(applyCount)
                    .startedTime(subscription.getStartedTime())
                    .endTime(subscription.getEndedTime())
                    .limitNum(subscription.getLimitNum())
                    .beanType(farm.getFarmBeanName())
                    .returnRate(subscription.getReturnRate())
                    .totalTokenCount(subscription.getTotalTokenCount())
                    .build();

            subscriptionListDtos.add(subscriptionListDto);
        }

        SubscriptionListResponse response = SubscriptionListResponse.builder()
                .subscriptions(subscriptionListDtos)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    public ResponseEntity<?> getSubscriptionDetail(int userCode, int subscriptionCode){
        User user = userRepository.findByUserCode(userCode);
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }


        Subscription subscriptionDetail = subscriptionRepository.findBySubscriptionCode(subscriptionCode);
        Integer applyCount = applyHistoryRepository.sumApplyCountBySubscriptionCode(subscriptionCode);
        applyCount = (applyCount != null) ? applyCount : 0;
        Farm farm = farmRepository.findByFarmCode(subscriptionDetail.getFarmCode());
        SubscriptionDetailResponse subscriptionDetailResponse = SubscriptionDetailResponse.builder()
                .subscriptionCode(subscriptionDetail.getSubscriptionCode())
                .farmCode(farm.getFarmCode())
                .farmName(farm.getFarmName())
                .confirmPrice(subscriptionDetail.getConfirmPrice())
                .startedTime(subscriptionDetail.getStartedTime())
                .endTime(subscriptionDetail.getEndedTime())
                .returnRate(subscriptionDetail.getReturnRate())
                .limitNum(subscriptionDetail.getLimitNum())
                .applyCount(applyCount)
                .totalTokenCount(subscriptionDetail.getTotalTokenCount())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(subscriptionDetailResponse);
    }

    private boolean isValidInput(Integer subsStatus, Integer minPrice, Integer maxPrice, String beanType) {

        // subsStatus 가 null 이 아니면서 0 미만이거나 2 초과다.
        if (subsStatus != null && (subsStatus < 0 || subsStatus > 2)) {
            return false;
        }
        // minPrice 와 maxPrice 가 모두 null 이 아니면서 minPrice 가 maxPrice 보다 크다.
        if (minPrice != null && maxPrice != null && minPrice > maxPrice) {
            return false;
        }

        // beanType 이 null 이 아니면서 허용 리스트에 포함되지 않는 값이면 false 처리
        return beanType == null || VALID_BEAN_TYPES.contains(beanType);
    }
}
