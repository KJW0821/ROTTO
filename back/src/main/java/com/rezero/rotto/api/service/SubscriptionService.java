package com.rezero.rotto.api.service;

import org.springframework.http.ResponseEntity;


public interface SubscriptionService {

    ResponseEntity<?> getSubscriptionList(int userCode);
    ResponseEntity<?> getSubscriptionDetail(int userCode, int subscriptionCode);
}
