package com.stylz.app.model;

import java.util.List;

public class Outfit {

    private String id;          // Firestore document ID
    private String userId;      // UID of the user who saved the outfit
    private List<String> items; // List of clothing item names/IDs

    public Outfit() {}

    public Outfit(String userId, List<String> items) {
        this.userId = userId;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
