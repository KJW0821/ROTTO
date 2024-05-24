package com.rezero.rotto.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;


// Firebase 설정을 관리하는 Config
@Configuration
public class FirebaseConfig {

    public static final String FIREBASE_ALARM_SEND_API_URI = "https://fcm.googleapis.com/v1/projects/rotto-4aa74/messages:send";
    public static final String FIREBASE_CONFIG_PATH = "../../resources/serviceAccountKey.json";

    // Firebase 를 초기화하는 메서드
    @Bean
    public FirebaseApp initializeFirebase() throws IOException {
        FileInputStream serviceAccount = new FileInputStream(FIREBASE_CONFIG_PATH);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        return FirebaseApp.initializeApp(options);
    }
}
