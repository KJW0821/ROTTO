package com.rezero.rotto.api.service;

import org.springframework.http.ResponseEntity;

public interface SubscriptionService {

    ResponseEntity<?>  getSupscriptionList(int userCode, Integer page);

}
