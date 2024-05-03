package com.rezero.rotto.api.service;


import com.rezero.rotto.repository.ApplyHistoryRepository;
import com.rezero.rotto.repository.SubscriptionRepository;
import com.rezero.rotto.repository.UserRepository;
import com.rezero.rotto.utils.Pagination;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplyHistoryServiceImpl implements ApplyHistoryService{

    private final ApplyHistoryRepository applyHistoryRepository;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final Pagination pagination;

    @Override
    public ResponseEntity<?> postApply(int useCode, int subscriptCode) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteApply(int userCode, int applyHistoryCode) {
        return null;
    }

    @Override
    public ResponseEntity<?> getApplyHistoryListByUser(int userCode, Integer page) {
        return null;
    }

    @Override
    public ResponseEntity<?> getApplyDetailByUser(int userCode, int subscriptionCode) {
        return null;
    }
}
