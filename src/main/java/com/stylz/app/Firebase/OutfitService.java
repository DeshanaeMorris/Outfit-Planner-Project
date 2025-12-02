package com.stylz.app.Firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.stylz.app.FirestoreContext;
import com.stylz.app.model.Outfit;

import java.util.concurrent.ExecutionException;

public class OutfitService {

    private final Firestore db;

    public OutfitService() {
        FirestoreContext.init();
        this.db = FirestoreContext.getFirestore();
    }

    public void saveOutfit(Outfit outfit) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentReference> future = db.collection("outfits").add(outfit);
    }
}
