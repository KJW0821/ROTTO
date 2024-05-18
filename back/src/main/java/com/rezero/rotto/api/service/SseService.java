package com.rezero.rotto.api.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SseService {

    SseEmitter subscribe(int userCode);

    void disConnect(int userCode);

}
