package com.stylz.app.Firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.stylz.app.Firebase.FirestoreDatabase;
import com.stylz.app.model.Outfit;

import java.util.concurrent.ExecutionException;

public class OutfitService {

    private final Firestore db = FirestoreDatabase.getFirestore();
    // Firestore is already initialized automatically;

    public void saveOutfit(Outfit outfit) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentReference> future = db.collection("outfits").add(outfit);
        System.out.println("Outfit saved to Firestore!");
    }
}
