package com.stylz.app;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import com.google.firebase.cloud.FirestoreClient;

import java.io.IOException;
import java.io.InputStream;

public class FirestoreContext {
    public static void init() {
        try {
            if (!FirebaseApp.getApps().isEmpty()) {
                return;
            }

            //Had to change FileInputStream to InputStream due to error
            InputStream serviceAccount =
                    FirestoreContext.class.getResourceAsStream("/com/stylz/app/FirebaseConfig.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);

            System.out.println("Firebase initialized!");

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public static Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }
}
