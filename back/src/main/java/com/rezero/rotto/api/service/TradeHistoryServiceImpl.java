package com.rezero.rotto.api.service;

import com.rezero.rotto.dto.dto.TradeHistoryExpenseDetailOfSubDto;
import com.rezero.rotto.dto.dto.TradeHistoryListDto;
import com.rezero.rotto.dto.response.TradeHistoryExpenseDetailOfSubResponse;
import com.rezero.rotto.dto.response.TradeHistoryListResponse;
import com.rezero.rotto.entity.*;
import com.rezero.rotto.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
    private final ExpenseDetailRepository expenseDetailRepository;

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
        User user = userRepository.findByUserCode(userCode);
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }

        Subscription subscription = subscriptionRepository.findBySubscriptionCode(subscriptionCode);
        TradeHistory tradeHistory = tradeHistoryRepository.findByUserCodeAndSubscriptionCode(userCode, subscriptionCode);
        List<ExpenseDetail> expenseDetails = expenseDetailRepository.findByFarmCode(subscription.getFarmCode());
        Farm farm =farmRepository.findByFarmCode(subscription.getFarmCode());

        int totalExpenses = expenseDetailRepository.sumExpenseDetailByFarmCode(farm.getFarmCode());
        List<TradeHistoryExpenseDetailOfSubDto> tradeHistoryExpenseDetailOfSubDtos = new ArrayList<>();

        for (ExpenseDetail expenseDetail : expenseDetails ) {
            TradeHistoryExpenseDetailOfSubDto tradeHistoryExpenseDetailOfSubDto = TradeHistoryExpenseDetailOfSubDto.builder()
                    .expenditureContent(expenseDetail.getExpenditureContent())
                    .expenses(expenseDetail.getExpenses())
                    .build();

            tradeHistoryExpenseDetailOfSubDtos.add(tradeHistoryExpenseDetailOfSubDto);
        }

        TradeHistoryExpenseDetailOfSubResponse tradeHistoryExpenseDetailOfSubResponse =TradeHistoryExpenseDetailOfSubResponse.builder()
                .subscriptionCode(subscription.getSubscriptionCode())
                .farmName(farm.getFarmName())
                .totalSoldPrice(farm.getTotalSales())
                .totalExpense(totalExpenses)
                .tradeTime(tradeHistory.getTradeTime())
                .tradeNum(tradeHistory.getTradeNum())
                .totalProceed(subscription.getTotalProceed())
                .refund(tradeHistory.getRefund())
                .totalTokenCount(subscription.getTotalTokenCount())
                .tradeHistoryExpenseDetailOfSubDtoList(tradeHistoryExpenseDetailOfSubDtos)
                .build();



    return ResponseEntity.status(HttpStatus.OK).body(tradeHistoryExpenseDetailOfSubResponse);
    }
}
