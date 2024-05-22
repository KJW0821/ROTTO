package com.rezero.rotto.api.service;

import com.rezero.rotto.entity.ApplyHistory;
import com.rezero.rotto.entity.Subscription;
import com.rezero.rotto.repository.ApplyHistoryRepository;
import com.rezero.rotto.repository.EmitterRepository;
import com.rezero.rotto.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class SseServiceImpl implements SseService {

    private final EmitterRepository emitterRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final ApplyHistoryRepository applyHistoryRepository;

    
    // SSE 구독 설정
    public SseEmitter subscribe(int userCode) {
        // 1시간 타임 아웃 설정
        long TIME_OUT = 60 * 60 * 1000L;
        SseEmitter sseEmitter = new SseEmitter(TIME_OUT);
        // SseEmitter 저장
        sseEmitter = emitterRepository.save(userCode, sseEmitter);

        // SSE 완료 이벤트 리스너 설정
        sseEmitter.onCompletion(() -> {
            log.info("disconnected by complete server sent event : id={}", userCode);
        });
        // SSE 타임아웃 이벤트 리스너 설정
        sseEmitter.onTimeout(() -> {
            log.info("server sent event timed out : id={}", userCode);
        });
        // SSE 에러 이벤트 리스너 설정
        sseEmitter.onError((e) -> {
            log.info("server sent event error occurred : id={}, message={}", userCode, e.getMessage());
            emitterRepository.deleteByUserCode(userCode); // 에러 발생 시 해당 사용자 코드로 저장된 Emitter 삭제
        });

        // 클라이언트에 연결 메시지 전송
        sendToClient(userCode, "connect", "SSE connected");

        return sseEmitter;
    }


    // 클라이언트로 데이터 전송
    public void sendToClient(int userCode, String name, Object data) {
        SseEmitter sseEmitter = emitterRepository.findByUserCode(userCode);

        if (sseEmitter != null) {
            try {
                log.info("send to client {}:[{}]", name, data);
                sseEmitter.send(SseEmitter.event()
                        .id(String.valueOf(userCode))
                        .name(name)
                        .data(data, MediaType.APPLICATION_JSON)); // JSON 형태로 데이터 전송
            } catch (IOException e) {
                log.error("failure to send event, id={}, message={}", userCode, e.getMessage());
                emitterRepository.deleteByUserCode(userCode); // 전송 실패 시 Emitter 삭제
            }
        }
    }


    // SSE 연결 종료
    public void disConnect(int userCode) {
        sendToClient(userCode, "disconnect", "SSE disconnected"); // 클라이언트에 연결 종료 메시지 전송
        emitterRepository.deleteByUserCode(userCode); // Emitter 삭제
    }


    /** 청약 시작일 오전 9시에 SSE 알림 보내기
     청약 시작일을 항상 09:00:00 으로 정의한다.
     **/
    @Scheduled(cron = "0 0 9 * * *")
    public void sendSubscriptionAlert() {
        log.info("Send subscription start alert");
        // 오늘 날짜 구하기
        LocalDateTime now = LocalDateTime.now().toLocalDate().atTime(9, 0, 0);

        // 청약 시작일이 오늘인 데이터 조회
        List<Subscription> subscriptions = subscriptionRepository.findByStartedTime(now);
        for (Subscription subscription : subscriptions) {
            // 청약 코드를 통해 해당 청약을 신청한 유저의 유저 코드 가져오기
            int subscriptionCode = subscription.getSubscriptionCode();
            // 청약 내역에서 삭제되지 않은 데이터를 가져옴
            Optional<List<ApplyHistory>> applyHistoriesOptional = applyHistoryRepository.findBySubscriptionCodeAndIsDelete(subscriptionCode, 0);
            // 청약 내역이 없으면 continue
            if (applyHistoriesOptional.isEmpty()) {
                continue;
            }
            List<ApplyHistory> applyHistories = applyHistoriesOptional.get();
            // 청약 내역 순회하면서 userCode 찾기
            for (ApplyHistory applyHistory : applyHistories) {
                int userCode = applyHistory.getUserCode();
                // 해당 유저에게 알림 보내기
                sendToClient(userCode, "청약 시작 알림", "청약이 시작되었습니다. 청약 코드: " + subscriptionCode);
            }
        }
    }


    /** 청약 시작 하루 전 오전 9시에 SSE 알림 보내기
     청약 시작일을 항상 09:00:00 으로 정의한다.
     **/
    @Scheduled(cron = "0 0 9 * * *")
    public void sendSubscriptionAlertOneDayBefore() {
        log.info("Send a alert a Day Before the subscription starts");
        // 내일 날짜 구하기
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow9AM = now.toLocalDate().plusDays(1).atTime(9, 0, 0);


        // 청약 시작 하루 전인 데이터 조회
        List<Subscription> subscriptions = subscriptionRepository.findByStartedTime(tomorrow9AM);
        for (Subscription subscription : subscriptions) {
            // 청약 코드를 통해 해당 청약을 신청한 유저의 유저 코드 가져오기
            int subscriptionCode = subscription.getSubscriptionCode();
            // 청약 내역에서 삭제되지 않은 데이터를 가져옴
            Optional<List<ApplyHistory>> applyHistoriesOptional = applyHistoryRepository.findBySubscriptionCodeAndIsDelete(subscriptionCode, 0);
            // 청약 내역이 없으면 continue
            if (applyHistoriesOptional.isEmpty()) {
                continue;
            }
            List<ApplyHistory> applyHistories = applyHistoriesOptional.get();
            // 청약 내역 순회하면서 userCode 찾기
            for (ApplyHistory applyHistory : applyHistories) {
                int userCode = applyHistory.getUserCode();
                // 해당 유저에게 알림 보내기
                sendToClient(userCode, "청약 시작 하루전 알림", "청약 시작 하루전입니다. 청약 코드: " + subscriptionCode);
            }
        }
    }


    /** 청약 시작 3일 전 오전 9시에 SSE 알림 보내기
     청약 시작일을 항상 09:00:00 으로 정의한다.
     **/
    @Scheduled(cron = "0 0 9 * * *")
    public void sendSubscriptionAlertThreeDaysBefore() {
        log.info("Send a alert 3 Days Before the subscription starts");
        // 3일 후 날짜 구하기
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threeDaysLaterAt9AM = now.toLocalDate().plusDays(3).atTime(9, 0, 0);


        // 청약 시작 3일 전인 데이터 조회
        List<Subscription> subscriptions = subscriptionRepository.findByStartedTime(threeDaysLaterAt9AM);
        for (Subscription subscription : subscriptions) {
            // 청약 코드를 통해 해당 청약을 신청한 유저의 유저 코드 가져오기
            int subscriptionCode = subscription.getSubscriptionCode();
            // 청약 내역에서 삭제되지 않은 데이터를 가져옴
            Optional<List<ApplyHistory>> applyHistoriesOptional = applyHistoryRepository.findBySubscriptionCodeAndIsDelete(subscriptionCode, 0);
            // 청약 내역이 없으면 continue
            if (applyHistoriesOptional.isEmpty()) {
                continue;
            }
            List<ApplyHistory> applyHistories = applyHistoriesOptional.get();
            // 청약 내역 순회하면서 userCode 찾기
            for (ApplyHistory applyHistory : applyHistories) {
                int userCode = applyHistory.getUserCode();
                // 해당 유저에게 알림 보내기
                sendToClient(userCode, "청약 시작 3일 전 알림", "청약 시작 3일 전입니다. 청약 코드: " + subscriptionCode);
            }
        }
    }


    /** 청약 시작 일주일 전 오전 9시에 SSE 알림 보내기
     청약 시작일을 항상 09:00:00 으로 정의한다.
     **/
    @Scheduled(fixedRate = 30000) // 30초마다 스케쥴 실행
    public void sendSubscriptionAlertOneWeekBefore() {
        log.info("Send a alert a Week Before the subscription starts");
        // 일주일 후 날짜 구하기
        LocalDateTime now = LocalDateTime.now();
        log.info("now: " + now);
        LocalDateTime oneWeekLaterAt9AM = now.toLocalDate().plusDays(7).atTime(9, 0, 0);
        log.info("oneWeekLaterAt9AM: " + oneWeekLaterAt9AM);

        // 청약 시작 일주일 전인 청약 조회
        List<Subscription> subscriptions = subscriptionRepository.findByStartedTime(oneWeekLaterAt9AM);
        for (Subscription subscription : subscriptions) {
            // 청약 코드를 통해 해당 청약을 신청한 유저의 유저 코드 가져오기
            int subscriptionCode = subscription.getSubscriptionCode();
            // 청약 내역에서 삭제되지 않은 데이터를 가져옴
            Optional<List<ApplyHistory>> applyHistoriesOptional = applyHistoryRepository.findBySubscriptionCodeAndIsDelete(subscriptionCode, 0);
            // 청약 내역이 없으면 continue
            if (applyHistoriesOptional.isEmpty()) {
                continue;
            }
            List<ApplyHistory> applyHistories = applyHistoriesOptional.get();
            // 청약 내역 순회하면서 userCode 찾기
            for (ApplyHistory applyHistory : applyHistories) {
                int userCode = applyHistory.getUserCode();
                // 해당 유저에게 알림 보내기
                sendToClient(userCode, "청약 시작 일주일 전 알림", "청약 시작 일주일 전입니다. 청약 코드: " + subscriptionCode);
                log.info("Successfully sent notification to user with userCode: " + userCode + ", subscriptionCode: " + subscriptionCode);
            }
        }
    }
}