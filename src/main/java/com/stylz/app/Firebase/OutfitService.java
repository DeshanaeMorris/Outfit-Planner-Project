package com.stylz.app.Firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.stylz.app.Firebase.FirestoreDatabase;
import com.stylz.app.model.Outfit;

import java.util.concurrent.ExecutionException;

public class OutfitService {

    private final Firestore db;

    public OutfitService() {
        // Initialize Firebase only once (the Database class handles the check)
        FirestoreDatabase.initializeFirebase();
        this.db = FirestoreDatabase.getFirestore();
    }

    public void saveOutfit(Outfit outfit) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentReference> future = db.collection("outfits").add(outfit);
        future.get(); //waits for Firestore to confirm the save
        System.out.println("Outfit saved to Firestore!");
    }


}
