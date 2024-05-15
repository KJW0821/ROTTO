package com.rezero.rotto.api.service;

import com.rezero.rotto.dto.dto.TradeHistoryListDto;
import com.rezero.rotto.dto.response.TradeHistoryListResponse;
import com.rezero.rotto.entity.Farm;
import com.rezero.rotto.entity.Subscription;
import com.rezero.rotto.entity.TradeHistory;
import com.rezero.rotto.entity.User;
import com.rezero.rotto.repository.FarmRepository;
import com.rezero.rotto.repository.SubscriptionRepository;
import com.rezero.rotto.repository.TradeHistoryRepository;
import com.rezero.rotto.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TradeHistoryServiceImpl implements TradeHistoryService{

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final FarmRepository farmRepository;
    private final TradeHistoryRepository tradeHistoryRepository;


    @Override
    public ResponseEntity<?> getTradeHistory(int userCode) {
        User user = userRepository.findByUserCode(userCode);
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }

        List<TradeHistory> tradeHistories = tradeHistoryRepository.findByUserCode(user.getUserCode());
        List<TradeHistoryListDto> tradeHistoryListDtos = new ArrayList<>();

        for (TradeHistory tradeHistory: tradeHistories) {
            Subscription subscription = subscriptionRepository.findBySubscriptionCode(tradeHistory.getSubscriptionCode());
            Farm farm =farmRepository.findByFarmCode(subscription.getFarmCode());

            TradeHistoryListDto tradeHistoryListDto = TradeHistoryListDto.builder()
                    .subscriptionCode(subscription.getSubscriptionCode())
                    .farmName(farm.getFarmName())
                    .confirmPrice(subscription.getConfirmPrice())
                    .tradeTime(tradeHistory.getTradeTime())
                    .tradeNum(tradeHistory.getTradeNum())
                    .refund(tradeHistory.getRefund())
                    .totalProceed(subscription.getTotalProceed())
                    .totalTokenCount(subscription.getTotalTokenCount())
                    .build();

            tradeHistoryListDtos.add(tradeHistoryListDto);
        }

        TradeHistoryListResponse response = TradeHistoryListResponse.builder()
                .tradeHistoryListDtoss(tradeHistoryListDtos)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<?> getExpenseDetailOfSub(int userCode, int subscriptionCode) {
        return null;
    }
}
