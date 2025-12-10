package com.stylz.app.model;

import com.google.cloud.Timestamp;

public class Outfit {

    private String id;          // Firestore document ID
    private String userId;      // UID of the user who saved the outfit
    private String modelPath;   // which base model image was used


    private String topKey;
    private String bottomKey;
    private String shoesKey;
    private String accessory1Key;
    private String accessory2Key;
    private String accessory3Key;
    private String accessory4Key;

    private Timestamp createdAt;
    private String snapshotUrl;    // URL of PNG in Firebase Storage

    public Outfit() {}

    public Outfit(String userId, String modelPath, String topKey, String bottomKey, String shoesKey, String accessory1Key, String accessory2Key, String accessory3Key, String accessory4Key, Timestamp createdAt, String snapshotUrl) {
        this.userId = userId;
        this.modelPath = modelPath;
        this.topKey = topKey;
        this.bottomKey = bottomKey;
        this.shoesKey = shoesKey;
        this.accessory1Key = accessory1Key;
        this.accessory2Key = accessory2Key;
        this.accessory3Key = accessory3Key;
        this.accessory4Key = accessory4Key;
        this.createdAt = createdAt;
        this.snapshotUrl = snapshotUrl;
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

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }

    public String getTopKey() {
        return topKey;
    }

    public void setTopKey(String topKey) {
        this.topKey = topKey;
    }

    public String getBottomKey() {
        return bottomKey;
    }

    public void setBottomKey(String bottomKey) {
        this.bottomKey = bottomKey;
    }

    public String getShoesKey() {
        return shoesKey;
    }

    public void setShoesKey(String shoesKey) {
        this.shoesKey = shoesKey;
    }

    public String getAccessory1Key() {
        return accessory1Key;
    }

    public void setAccessory1Key(String accessory1Key) {
        this.accessory1Key = accessory1Key;
    }

    public String getAccessory2Key() {
        return accessory2Key;
    }

    public void setAccessory2Key(String accessory2Key) {
        this.accessory2Key = accessory2Key;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getAccessory3Key() {
        return accessory3Key;
    }

    public void setAccessory3Key(String accessory3Key) {
        this.accessory3Key = accessory3Key;
    }

    public String getAccessory4Key() {
        return accessory4Key;
    }

    public void setAccessory4Key(String accessory4Key) {
        this.accessory4Key = accessory4Key;
    }

    public String getSnapshotUrl() {
        return snapshotUrl;
    }

    public void setSnapshotUrl(String snapshotUrl) {
        this.snapshotUrl = snapshotUrl;
    }
}
