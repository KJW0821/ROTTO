package com.rezero.rotto.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


// Firebase 설정을 관리하는 Config
@Configuration
public class FirebaseConfig {

    public static final String FIREBASE_ALARM_SEND_API_URI = "https://fcm.googleapis.com/v1/projects/rotto-4aa74/messages:send";
    public static final String FIREBASE_CONFIG_PATH = "serviceAccountKey.json";

    /**
     * resources/serviceAccountKey.json
     * java/com/rezero/rotto/config/FirebaseConfig.java
     * **/

    // Firebase 를 초기화하는 메서드
    @Bean
    public FirebaseApp initializeFirebase() throws IOException {
        InputStream serviceAccount = null;
        try {
            // 클래스패스로부터 리소스를 가져옴
            serviceAccount = getClass().getResourceAsStream(FIREBASE_CONFIG_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            return FirebaseApp.initializeApp(options);

        }
    }

}
