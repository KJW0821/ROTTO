package com.rezero.rotto.api.service;

import org.springframework.http.ResponseEntity;


public interface SubscriptionService {

    ResponseEntity<?> getSubscriptionList(int userCode, Integer page);
    ResponseEntity<?> getSubscriptionDetail(int userCode, int subscriptionCode);
}
