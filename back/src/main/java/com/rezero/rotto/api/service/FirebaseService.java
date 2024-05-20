package com.rezero.rotto.api.service;

public interface FirebaseService {

    void sendScheduledNotification();
    void sendMessage(String topic, String title, String body);

}
