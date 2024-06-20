package com.ou.configs;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void initialize() {
        try {

            GoogleCredentials googleCredentials = GoogleCredentials.fromStream(
                    new ClassPathResource("firebase-service-account.json").getInputStream()
            );
            FirebaseOptions firebaseOptions = FirebaseOptions.builder()
                    .setCredentials(googleCredentials)
                    .build();


            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(firebaseOptions);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
