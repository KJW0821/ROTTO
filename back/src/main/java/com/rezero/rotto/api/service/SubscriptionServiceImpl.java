package com.rezero.rotto.api.service;

import com.rezero.rotto.dto.dto.SubscriptionListDto;
import com.rezero.rotto.dto.request.AccountDepositRequest;
import com.rezero.rotto.dto.request.PayTokensRequest;
import com.rezero.rotto.dto.response.SubscriptionDetailResponse;
import com.rezero.rotto.dto.response.SubscriptionListResponse;
import com.rezero.rotto.entity.Account;
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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

@Service
@RequiredArgsConstructor
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService{

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final FarmRepository farmRepository;
    private final ApplyHistoryRepository applyHistoryRepository;
    private final BlockChainService blockChainService;
    private final TradeHistoryRepository tradeHistoryRepository;
    private final AccountService accountService;

    public ResponseEntity<?> getSubscriptionList(int userCode){
        User user = userRepository.findByUserCode(userCode);
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }

        List<Subscription> subscriptions = subscriptionRepository.findAll();
        List<SubscriptionListDto> subscriptionListDtos = new ArrayList<>();


        for (Subscription subscription : subscriptions) {
            Farm farm = farmRepository.findByFarmCode(subscription.getFarmCode());
            SubscriptionListDto subscriptionListDto = SubscriptionListDto.builder()
                    .subscriptionCode(subscription.getSubscriptionCode())
                    .farmCode(farm.getFarmCode())
                    .farmName(farm.getFarmName())
                    .confirmPrice(subscription.getConfirmPrice())
                    .applyCount(subscription.getApplyCount())
                    .endTime(subscription.getEndedTime())
                    .limitNum(subscription.getLimitNum())
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
                .applyCount(subscriptionDetail.getApplyCount())
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
            // if(responseEntity.getStatusCode() != HttpStatus.OK){
            //
            // }

            // 받지 못한 토큰의 값어치만큼 환불
            int refundTokenCount = history.getApplyCount() - count;
            if(refundTokenCount > 0){ // 환불을 받아야 할 경우
                String refundValue = String.valueOf(subscription.getConfirmPrice() * refundTokenCount);
                AccountDepositRequest depositRequest = new AccountDepositRequest(refundValue);
                ResponseEntity<?> refundResponseEntity = accountService.patchAccountDeposit(user.getUserCode(), depositRequest);
            }
        }


        return null;
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

}
