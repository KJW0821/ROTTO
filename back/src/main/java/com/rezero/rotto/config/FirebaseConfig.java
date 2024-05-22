//package com.rezero.rotto.config;
//
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PostConstruct;
//import java.io.FileInputStream;
//
//@Configuration
//public class FirebaseConfig {
//
//    public static final String FIREBASE_ALARM_SEND_API_URI = "https://fcm.googleapis.com/v1/projects/rotto-4aa74/messages:send";
//    public static final String FIREBASE_CONFIG_PATH = "src/main/resources/serviceAccountKey.json";
//
//    @PostConstruct
//    public void initialize() {
//        try {
//            FileInputStream serviceAccount = new FileInputStream("src/main/resources/serviceAccountKey.json");
//            FirebaseOptions.Builder optionBuilder = FirebaseOptions.builder();
//            FirebaseOptions options = optionBuilder
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .build();
//            FirebaseApp.initializeApp(options);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
