package com.rezero.rotto.api.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.rezero.rotto.dto.request.FirebaseNotificationRequest;
import com.rezero.rotto.entity.InterestFarm;
import com.rezero.rotto.entity.Subscription;
import com.rezero.rotto.entity.User;
import com.rezero.rotto.repository.FarmRepository;
import com.rezero.rotto.repository.InterestFarmRepository;
import com.rezero.rotto.repository.SubscriptionRepository;
import com.rezero.rotto.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final PushNotificationService pushNotificationService;

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


    public ResponseEntity<?> sendMessageTo(int userCode, FirebaseNotificationRequest request) {

        //알림 요청 받는 사람의 FCM Token이 존재하는지 확인
        User user = userRepository.findByUserCode(userCode);
        String deviceToken = user.getDeviceToken();
        if (deviceToken == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("기기 정보가 존재하지 않습니다.");
        }

        try {
            pushNotificationService.sendPushNotification(deviceToken, request.getTopic(), request.getTitle(), request.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 문제로 푸시 알림 전송에 실패하였습니다.");
        }

        return ResponseEntity.status(HttpStatus.OK).body("푸시 알림 전송 완료");
    }

}
