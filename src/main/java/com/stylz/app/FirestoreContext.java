package com.stylz.app;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;

public class FirestoreContext {
    public static void init() {
        try {
            if (!FirebaseApp.getApps().isEmpty()) {
                return;
            }

            FileInputStream serviceAccount =
                    new FileInputStream("src/main/resources/com/stylz/app/key.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);

            System.out.println("Firebase initialized!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }
}
