package com.rezero.rotto.api.service;

import org.springframework.http.ResponseEntity;


public interface SubscriptionService {

    ResponseEntity<?> getSubscriptionList(int userCode, Integer subsStatus, Integer minPrice, Integer maxPrice, String beanType, String sort, String keyword);
    ResponseEntity<?> getSubscriptionDetail(int userCode, int subscriptionCode);
}
