package com.rezero.rotto.api.service;

import com.rezero.rotto.repository.EmitterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class SseServiceImpl implements SseService {

    private final EmitterRepository emitterRepository;

    public SseEmitter subscribe(int userCode) {
        long TIME_OUT = 60 * 60 * 1000L;
        SseEmitter sseEmitter = new SseEmitter(TIME_OUT);
        sseEmitter = emitterRepository.save(userCode, sseEmitter);

        sseEmitter.onCompletion(() -> {
            log.info("disconnected by complete server sent event : id={}", userCode);
        });
        sseEmitter.onTimeout(() -> {
            log.info("server sent event timed out : id={}", userCode);
        });
        sseEmitter.onError((e) -> {
            log.info("server sent event error occurred : id={}, message={}", userCode, e.getMessage());
            emitterRepository.deleteByUserCode(userCode);
        });

        sendToClient(userCode, "connect", "SSE connected");

        return sseEmitter;
    }


    public void sendToClient(int userCode, String name, Object data) {
        SseEmitter sseEmitter = emitterRepository.findByUserCode(userCode);

        if (sseEmitter != null) {
            try {
                log.info("send to client {}:[{}]", name, data);
                sseEmitter.send(SseEmitter.event()
                        .id(String.valueOf(userCode))
                        .name(name)
                        .data(data, MediaType.APPLICATION_JSON));
            } catch (IOException e) {
                log.error("failure to send event, id={}, message={}", userCode, e.getMessage());
                emitterRepository.deleteByUserCode(userCode);
            }
        }
    }


    public void disConnect(int userCode) {
        sendToClient(userCode, "disconnect", "SSE disconnected");
        emitterRepository.deleteByUserCode(userCode);
    }
}
