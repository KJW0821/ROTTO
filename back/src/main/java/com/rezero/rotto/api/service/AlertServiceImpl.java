package com.rezero.rotto.api.service;


import com.rezero.rotto.repository.AlertRepository;
import com.rezero.rotto.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AlertServiceImpl {

    private final UserRepository userRepository;
    private final AlertRepository alertRepository;

}
