package com.stylz.app.Firebase;

public class UserSession {
    private static String userId;

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String id) {
        userId = id;
    }
}