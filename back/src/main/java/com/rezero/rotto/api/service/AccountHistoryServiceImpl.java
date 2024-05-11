package com.rezero.rotto.api.service;

import com.rezero.rotto.dto.dto.AccountHistoryListDto;
import com.rezero.rotto.dto.response.AccountHistoryListResponse;
import com.rezero.rotto.entity.AccountHistory;
import com.rezero.rotto.entity.User;
import com.rezero.rotto.repository.AccountHistoryRepository;
import com.rezero.rotto.repository.UserRepository;
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
public class AccountHistoryServiceImpl implements AccountHistoryService{

    private final UserRepository userRepository;
    private final AccountHistoryRepository accountHistoryRepository;

    @Override
    public ResponseEntity<?> getAccountHistory(int userCode, int accountCode) {
        User user = userRepository.findByUserCode(userCode);
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }

        List<AccountHistory> accountHistoryList = accountHistoryRepository.findByAccountCode(accountCode);
        Collections.reverse(accountHistoryList);
        List<AccountHistoryListDto> accountHistoryListDtos = new ArrayList<>();

        for (int i = 0; i < accountHistoryList.size(); i++){
            AccountHistoryListDto accountHistoryListDto = AccountHistoryListDto.builder()
                    .transferName(user.getName())
                    .amount(accountHistoryList.get(i).getAmount())
                    .accountTime(accountHistoryList.get(i).getAccountTime())
                    .depositOrWithdrawal(accountHistoryList.get(i).getDepositOrWithdrawal())
                    .build();
            accountHistoryListDtos.add(accountHistoryListDto);
        }

        AccountHistoryListResponse accountHistoryListResponse = AccountHistoryListResponse.builder()
                .accountHistoryListDtoss(accountHistoryListDtos)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(accountHistoryListResponse);
    }
}
