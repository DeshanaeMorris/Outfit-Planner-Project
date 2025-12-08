package com.stylz.app.Firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;


import java.io.InputStream;

public class FirestoreDatabase {
    private static boolean initialized = false;

    public static void initializeFirebase() {
        if (initialized) {
            System.out.println("Firebase already initialized.");
            return;
        }

        try {
            System.out.println("Initializing Firebase...");

            InputStream serviceAccount = FirestoreDatabase.class
                    .getClassLoader()
                    .getResourceAsStream("com/stylz/app/key.json");

            if (serviceAccount == null) {
                throw new RuntimeException("key.json NOT FOUND in resources!");
            }
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
            initialized = true;

            System.out.println("Firebase successfully connected!");

        } catch (Exception e) {
            System.out.println("Error initializing Firebase:");
            e.printStackTrace();
        }
    }

    public static Firestore getFirestore() {
        if (!initialized) {
            initializeFirebase();
        }
        return FirestoreClient.getFirestore();
    }
}
