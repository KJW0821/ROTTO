package com.rezero.rotto.api.service;

import com.rezero.rotto.dto.dto.SubscriptionListDto;
import com.rezero.rotto.dto.request.AccountDepositRequest;
import com.rezero.rotto.dto.request.CreateTokenRequest;
import com.rezero.rotto.dto.request.PayTokensRequest;
import com.rezero.rotto.dto.response.SubscriptionDetailResponse;
import com.rezero.rotto.dto.response.SubscriptionListResponse;
import com.rezero.rotto.entity.Account;

import com.rezero.rotto.dto.request.SubscriptionProduceRequest;


import com.rezero.rotto.entity.ApplyHistory;
import com.rezero.rotto.entity.Farm;
import com.rezero.rotto.entity.Subscription;
import com.rezero.rotto.entity.TradeHistory;
import com.rezero.rotto.entity.User;
import com.rezero.rotto.repository.ApplyHistoryRepository;
import com.rezero.rotto.repository.FarmRepository;
import com.rezero.rotto.repository.SubscriptionRepository;
import com.rezero.rotto.repository.TradeHistoryRepository;
import com.rezero.rotto.repository.UserRepository;
import com.rezero.rotto.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Random;


import java.time.LocalDateTime;
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
    private final BlockChainService blockChainService;
    private final TradeHistoryRepository tradeHistoryRepository;
    private final AccountService accountService;

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


    @Override
    public ResponseEntity<?> calculateSubscription(int subscriptionCode) {
        Subscription subscription = subscriptionRepository.findBySubscriptionCode(subscriptionCode);
        if(subscription == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 청약입니다.");
        }

        Optional<List<ApplyHistory>> applyHistories =
            applyHistoryRepository.findBySubscriptionCodeAndIsDelete(subscriptionCode, 0);

        if(applyHistories.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 청약의 신청자를 찾을 수 없습니다.");
        }

        // 신청자마다 발급받을 ROTTO의 개수 파악
        HashMap<Integer, Integer> map = EqualDistribution(subscription, applyHistories.get());

        // 사용자의 지갑에 ROTTO 발급 (with. Smart Contract)
        for(ApplyHistory history : applyHistories.get()){
            int count = map.get(history.getUserCode()); // 발급받을 ROTTO 개수
            if(count == 0) continue;
            User user = userRepository.findByUserCode(history.getUserCode());
            String walletAddress = user.getBcAddress();
            PayTokensRequest request = new PayTokensRequest();
            request.setCode(subscription.getSubscriptionCode());
            request.setAddress(walletAddress);
            request.setAmount(count);

            // ROTTO 발급 수행
            ResponseEntity<?> responseEntity = blockChainService.distributeToken(request);
             if(responseEntity.getStatusCode() != HttpStatus.OK){
                return responseEntity;
             }

            // 받지 못한 토큰의 값어치만큼 환불
            int refundTokenCount = history.getApplyCount() - count;
            if(refundTokenCount > 0){ // 환불을 받아야 할 경우
                String refundValue = String.valueOf(subscription.getConfirmPrice() * refundTokenCount);
                AccountDepositRequest depositRequest = new AccountDepositRequest(refundValue);
                ResponseEntity<?> refundResponseEntity = accountService.patchAccountDeposit(user.getUserCode(), depositRequest);
            }
        }


        return ResponseEntity.ok().body("청약 ROTTO 발급 완료");
    }

    // 균등배분을 수행함.
    private HashMap<Integer, Integer> EqualDistribution(Subscription subscription, List<ApplyHistory> list) {
        Random random = new Random();
        Queue<ApplyHistory> queue = new ArrayDeque<>();
        HashMap<Integer, Integer> result = new HashMap<Integer, Integer>(); // userCode, 토큰 부여 개수
        for(ApplyHistory history : list){
            int userCode = history.getUserCode();
            result.put(userCode, 0);
            queue.offer(history);
        }

        int totalTokenCount = subscription.getTotalTokenCount();
        int limit = subscription.getLimitNum();

        // 총 발생 토큰 수보다 신청자 수가 더 많은 경우
        if(totalTokenCount < list.size()){
            List<ApplyHistory> randomList = new ArrayList<>(list);
            for(int i = 0; i < totalTokenCount; i++){
                int randomIndex = random.nextInt(randomList.size());
                ApplyHistory randomHistory = randomList.get(randomIndex);
                result.replace(randomHistory.getUserCode(), 1);
                randomList.remove(randomIndex);
            }
        }
        else {
            int currentCount = totalTokenCount;
            do{
                int size = queue.size();
                int NCount = currentCount / size;
                currentCount -= NCount * size;
                for(int i = 0; i < size; i++){
                    ApplyHistory history = queue.poll();
                    int applyCount = history.getApplyCount();
                    int temp = result.get(history.getUserCode()) + NCount; // 현재 개수 + n
                    int getTokens = Math.min(temp, applyCount);
                    result.replace(history.getUserCode(), getTokens);

                    if(getTokens < limit)   queue.offer(history);
                    else currentCount += (temp - applyCount);
                }
            }while(!queue.isEmpty() && currentCount >= list.size());

            // 남은 토큰 개수 < 균등하게 배분해야 할 인원 수
            if(!queue.isEmpty()){
                List<ApplyHistory> randomList = new ArrayList<>(queue);
                for(int i = 0; i < currentCount; i++){
                    int randomIndex = random.nextInt(randomList.size());
                    ApplyHistory randomHistory = randomList.get(randomIndex);
                    result.replace(randomHistory.getUserCode(), result.get(randomHistory.getUserCode()) + 1);
                    randomList.remove(randomIndex);
                }
            }
        }

        return result;
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

        // 청약 ROTTO 생성
        CreateTokenRequest request = new CreateTokenRequest();
        request.setCode(subscription.getSubscriptionCode());
        request.setAmount(subscription.getTotalTokenCount());
        ResponseEntity<?> blockChainResponseEntity = blockChainService.createToken(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(subscription);
    }

}
