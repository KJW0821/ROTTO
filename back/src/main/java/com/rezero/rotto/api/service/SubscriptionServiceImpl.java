package com.rezero.rotto.api.service;

import com.rezero.rotto.dto.dto.SubscriptionListDto;
import com.rezero.rotto.dto.request.SubscriptionProduceRequest;
import com.rezero.rotto.dto.response.SubscriptionDetailResponse;
import com.rezero.rotto.dto.response.SubscriptionListResponse;
import com.rezero.rotto.entity.ApplyHistory;
import com.rezero.rotto.entity.Farm;
import com.rezero.rotto.entity.Subscription;
import com.rezero.rotto.entity.User;
import com.rezero.rotto.repository.*;
import com.rezero.rotto.utils.Pagination;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
    private final Pagination pagination;


    public ResponseEntity<?> getSubscriptionList(int userCode, Integer page, Integer subsStatus, Integer minPrice, Integer maxPrice, String beanType, String sort, String keyword){
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
        // 최신순으로 보여주기 위해 리스트 뒤집기
        Collections.reverse(subscriptions);
        // 페이지네이션
        List<Subscription> pageSubscriptions = subscriptions.subList(startIdx, endIdx);
        List<SubscriptionListDto> subscriptionListDtos = new ArrayList<>();

        for (Subscription subscription : pageSubscriptions) {
            Farm farm = farmRepository.findByFarmCode(subscription.getFarmCode());
            int subscriptionCode = subscription.getSubscriptionCode();
            Integer applyCount = applyHistoryRepository.sumApplyCountBySubscriptionCode(subscriptionCode);
            applyCount = (applyCount != null) ? applyCount : 0;

            // 현재 시각
            LocalDateTime now = LocalDateTime.now();

            // 시작 시간과 종료 시간
            LocalDateTime startedTime = subscription.getStartedTime();
            LocalDateTime endTime = subscription.getEndedTime();

            // substatus 값을 결정하는 로직
            int substatus;
            if (now.isBefore(startedTime)) {
                substatus = 0; // 현재 시각이 시작 시간보다 이전
            } else if (now.isAfter(endTime)) {
                substatus = 2; // 현재 시각이 종료 시간보다 이후
            } else {
                substatus = 1; // 현재 시각이 시작 시간과 종료 시간 사이
            }

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
                    .subsStatus(substatus)
                    .build();

            subscriptionListDtos.add(subscriptionListDto);
        }

        SubscriptionListResponse response = SubscriptionListResponse.builder()
                .subscriptions(subscriptionListDtos)
                .totalPages(totalPages)
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
        ApplyHistory applyHistory1 = applyHistoryRepository.findByUserCodeAndSubscriptionCode(userCode, subscriptionCode);

        int isApplies = 0;
        if (applyHistory1 != null && subscriptionCode == applyHistory1.getSubscriptionCode()){
            isApplies = 1;
        }

        // 현재 시각
        LocalDateTime now = LocalDateTime.now();

        // 시작 시간과 종료 시간
        LocalDateTime startedTime = subscriptionDetail.getStartedTime();
        LocalDateTime endTime = subscriptionDetail.getEndedTime();

        // substatus 값을 결정하는 로직
        int substatus;
        if (now.isBefore(startedTime)) {
            substatus = 0; // 현재 시각이 시작 시간보다 이전
        } else if (now.isAfter(endTime)) {
            substatus = 2; // 현재 시각이 종료 시간보다 이후
        } else {
            substatus = 1; // 현재 시각이 시작 시간과 종료 시간 사이
        }

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
                .subsStatus(substatus)
                .farmImg(farm.getFarmLogoPath())
                .isApply(isApplies)
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

    @Override
    public ResponseEntity<?> postSubscription(int userCode, SubscriptionProduceRequest subscriptionProduceRequest) {
        User user = userRepository.findByUserCode(userCode);
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }

        // admin 값이 0이면
        if (!user.getAdmin()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("접근권한이 없습니다.");
        }

        // SubscriptionProduceRequest 유효성 검사
        if (subscriptionProduceRequest.getFarmCode() <= 0 ||
                subscriptionProduceRequest.getConfirmPrice() <= 0 ||
                subscriptionProduceRequest.getLimitNum() <= 0 ||
                subscriptionProduceRequest.getReturnRate() <= 0 ||
                subscriptionProduceRequest.getPartnerFarmRate() <= 0 ||
                subscriptionProduceRequest.getTotalTokenCount() <= 0 ||
                subscriptionProduceRequest.getStartedTime() == null ||
                subscriptionProduceRequest.getEndedTime() == null ||
                subscriptionProduceRequest.getStartedTime().isAfter(subscriptionProduceRequest.getEndedTime())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 요청입니다.");
        }

        Subscription subscription = new Subscription();
        subscription.setFarmCode(subscriptionProduceRequest.getFarmCode());
        subscription.setConfirmPrice(subscriptionProduceRequest.getConfirmPrice());
        subscription.setStartedTime(subscriptionProduceRequest.getStartedTime());
        subscription.setEndedTime(subscriptionProduceRequest.getEndedTime());
        subscription.setLimitNum(subscriptionProduceRequest.getLimitNum());
        subscription.setReturnRate(subscription.getReturnRate());
        subscription.setTotalTokenCount(subscription.getTotalTokenCount());
        subscription.setPartnerFarmRate(subscription.getPartnerFarmRate());
        subscriptionRepository.save(subscription);


        return ResponseEntity.status(HttpStatus.CREATED).body(subscription);
    }
}
