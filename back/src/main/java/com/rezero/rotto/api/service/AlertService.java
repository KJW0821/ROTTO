package com.rezero.rotto.api.service;

import org.springframework.http.ResponseEntity;

public interface AlertService {

    ResponseEntity<?> getAlertList(int userCode);

}
