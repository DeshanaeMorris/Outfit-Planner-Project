package com.stylz.app.Firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.stylz.app.model.Outfit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class OutfitService {

    private final Firestore db;

    public OutfitService() {
        // Initialize Firebase only once
        FirestoreDatabase.initializeFirebase();
        this.db = FirestoreDatabase.getFirestore();
    }

    public void saveOutfit(Outfit outfit) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentReference> future = db.collection("outfits").add(outfit);
        DocumentReference docRef = future.get(); //waits for Firestore to confirm the save
        outfit.setId(docRef.getId());

        System.out.println("Outfit saved to Firestore with id = " + outfit.getId());
    }

    //get all outfits saved by a specific user
    public List<Outfit> getOutfitsForUser(String userId) throws ExecutionException, InterruptedException {
        List<Outfit> outfits = new ArrayList<>();

        ApiFuture<QuerySnapshot> future = db.collection("outfits")
                .whereEqualTo("userId", userId)
                .get();

        QuerySnapshot snapshot = future.get();

        for (QueryDocumentSnapshot doc : snapshot.getDocuments()) {
            outfits.add(toOutfit(doc));
        }

        System.out.println("Loaded " + outfits.size() + " outfits for user " + userId);
        return outfits;
    }

    public List<Outfit> getLastOutfitsForUser(String userId, int limit)
            throws ExecutionException, InterruptedException {

        List<Outfit> outfits = new ArrayList<>();

        ApiFuture<QuerySnapshot> future = db.collection("outfits")
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get();

        QuerySnapshot snapshot = future.get();

        for (QueryDocumentSnapshot doc : snapshot.getDocuments()) {
            Outfit outfit = toOutfit(doc);

            if (userId.equals(outfit.getUserId())) {
                outfits.add(outfit);
                if (outfits.size() >= limit) {
                    break;
                }
            }
        }

        System.out.println("Loaded " + outfits.size() + " most recent outfits for user " + userId);
        return outfits;
    }
    private Outfit toOutfit(QueryDocumentSnapshot doc) {
        Outfit outfit = doc.toObject(Outfit.class);
        outfit.setId(doc.getId());
        return outfit;
    }
}
