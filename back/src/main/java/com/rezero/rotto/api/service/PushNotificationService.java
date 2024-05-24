package com.rezero.rotto.api.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

// 푸시 알림을 보내는 Service
@Service
@RequiredArgsConstructor
@Slf4j
public class PushNotificationService {

    /**
     * 주어진 FCM 토큰 리스트의 모든 유저에게 푸시 알림을 전송하는 메서드
     *
     * @param tokens FCM 토큰 리스트
     * @param title  알림 제목
     * @param body   알림 내용
     */
    public void sendPushNotificationToAllUsers(List<String> tokens, String title, String body) {
        for (String token : tokens) {
            sendPushNotificationToAllUsers(tokens, title, body);
        }
    }


    /**
     * 특정 FCM 토큰을 가진 유저에게 푸시 알림을 전송하는 메서드
     *
     * @param token FCM 토큰
     * @param title 알림 제목
     * @param body  알림 내용
     */
    public void sendPushNotification(String token, String title, String body) {
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        Message message = Message.builder()
                .setToken(token)
                .setNotification(notification)
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            log.info("Successfully sent message: " + response);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
    }
}
