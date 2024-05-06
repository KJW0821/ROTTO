package com.rezero.rotto.api.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.rezero.rotto.entity.InterestFarm;
import com.rezero.rotto.entity.Subscription;
import com.rezero.rotto.entity.User;
import com.rezero.rotto.repository.FarmRepository;
import com.rezero.rotto.repository.InterestFarmRepository;
import com.rezero.rotto.repository.SubscriptionRepository;
import com.rezero.rotto.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.firebase.messaging.Notification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FirebaseServiceImpl implements FirebaseService {

    private final SubscriptionRepository subscriptionRepository;
    private final FarmRepository farmRepository;
    private final InterestFarmRepository interestFarmRepository;
    private final UserRepository userRepository;

    @Scheduled(cron = "0 0 9 * * ?")
    public void sendScheduledNotification() {
        // 오늘 날짜
        LocalDate today = LocalDate.now(ZoneId.systemDefault());

        // 어제 날짜 계산
        LocalDate yesterday = today.minusDays(1);

        // 내일 날짜 계산
        LocalDate tomorrow = today.plusDays(1);

        // 현재 시스템 시간
        LocalDateTime now = LocalDateTime.now();

        List<Subscription> subscriptions = subscriptionRepository.findAll();
        for (Subscription subscription : subscriptions) {
            LocalDate subscriptionStartDate = subscription.getStartedTime().toLocalDate();

            List<InterestFarm> interestFarms = interestFarmRepository.findAll();
            for (InterestFarm interestFarm : interestFarms) {
                User user = userRepository.findByUserCode(interestFarm.getUserCode());
                if (user != null) {
                    if (subscription.getEndedTime() != null && subscription.getEndedTime().toLocalDate().equals(yesterday)) {
                        sendMessage(user.getDeviceToken(), "End Time Reminder", "Your interested subscription ended yesterday.");
                    }
                    if (subscription.getStartedTime() != null && subscriptionStartDate.equals(today)) {
                        sendMessage(user.getDeviceToken(), "Start Time Reminder", "Your interested subscription starts today.");
                    }
                    if (subscription.getStartedTime() != null && subscriptionStartDate.equals(tomorrow)) {
                        sendMessage(user.getDeviceToken(), "Upcoming Start Time Reminder", "Your interested subscription starts tomorrow at 9 AM.");
                    }
                }
            }
        }
    }

    public void sendMessage(String topic, String title, String body) {
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();
        Message message = Message.builder()
                .setNotification(notification)
                .setTopic(topic)
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Successfully sent message: " + response);
        } catch (FirebaseMessagingException e) {
            System.err.println("Error sending message: " + e.getMessage());
        }
    }


//    public void sendMessageTo(final Long receiverId, final Notification notification) {
//
//        //알림 요청 받는 사람의 FCM Token이 존재하는지 확인
//        final FcmToken fcmToken = fcmTokenRepository.findByMemberId(receiverId)
//                .orElseThrow(() -> new NotificationException(NOT_FOUND_FCM_TOKEN));
//
//        //메시지 만들기
//        final String message = makeMessage(fcmToken.getToken(), notification);
//
//        final HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//        //OAuth 2.0 사용
//        httpHeaders.add(HttpHeaders.AUTHORIZATION, PREFIX_ACCESS_TOKEN + getAccessToken());
//
//        final HttpEntity<String> httpEntity = new HttpEntity<>(message, httpHeaders);
//
//        final String fcmRequestUrl = PREFIX_FCM_REQUEST_URL + projectId + POSTFIX_FCM_REQUEST_URL;
//
//        final ResponseEntity<String> exchange = restTemplate.exchange(
//                fcmRequestUrl,
//                HttpMethod.POST,
//                httpEntity,
//                String.class
//        );
//
//        if (exchange.getStatusCode().isError()) {
//            log.error("firebase 접속 에러 = {}", exchange.getBody());
//        }
//    }

}
