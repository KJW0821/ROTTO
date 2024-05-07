package com.rezero.rotto.api.service;


import com.rezero.rotto.entity.ApplyHistory;
import com.rezero.rotto.entity.Subscription;
import com.rezero.rotto.entity.User;
import com.rezero.rotto.repository.ApplyHistoryRepository;
import com.rezero.rotto.repository.SubscriptionRepository;
import com.rezero.rotto.repository.UserRepository;
import com.rezero.rotto.utils.Pagination;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }


        ApplyHistory applyHistory = new ApplyHistory();
        Subscription subscription = subscriptionRepository.findBySubscriptionCode(subscriptCode);

        applyHistory.setApplyTime();


        return null;
    }

    @Override
    public ResponseEntity<?> deleteApply(int userCode, int applyHistoryCode) {

        return null;
    }

}
