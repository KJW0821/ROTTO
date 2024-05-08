package com.rezero.rotto.api.service;

//import com.rezero.rotto.dto.request.RegisterPinRequest;

import com.rezero.rotto.dto.request.CheckPhoneNumRequest;
import com.rezero.rotto.dto.request.SignUpRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> signUp(SignUpRequest request);
//    ResponseEntity<?> registerPin(int userCode, RegisterPinRequest request);
//    ResponseEntity<?> registerPin(int userCode, String oldPin, String newPin);
    ResponseEntity<?> getUserInfo(int userCode);

    ResponseEntity<String> deleteUser(int userCode);

    ResponseEntity<?> checkPhoneNum(CheckPhoneNumRequest request);

}
