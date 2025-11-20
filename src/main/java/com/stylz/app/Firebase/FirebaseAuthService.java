package com.stylz.app.Firebase;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.*;
import java.io.IOException;

public class FirebaseAuthService {
    private static final String API_KEY = "AIzaSyBESor64BavEICoPXFx29qS6nc8vcKEW_0";
    private static final String LOGIN_URL =
            "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + API_KEY;

    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new Gson();

    public static String login(String email, String password) {

        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("email", email);
        jsonObj.addProperty("password", password);
        jsonObj.addProperty("returnSecureToken", true);

        RequestBody body = RequestBody.create(
                jsonObj.toString(),
                MediaType.get("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(LOGIN_URL)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful()) {
                System.out.println("Login failed: " + response);
                return null;
            }

            String responseString = response.body().string();

            JsonObject jsonResponse = gson.fromJson(responseString, JsonObject.class);

            return jsonResponse.get("idToken").getAsString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    private static final String SIGNUP_URL =
            "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + API_KEY;

    public static String register(String email, String password) {

        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("email", email);
        jsonObj.addProperty("password", password);
        jsonObj.addProperty("returnSecureToken", true);

        RequestBody body = RequestBody.create(
                jsonObj.toString(),
                MediaType.get("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(SIGNUP_URL)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful()) {
                System.out.println("Sign-Up failed: " + response);
                return null;
            }

            String responseString = response.body().string();
            JsonObject jsonResponse = gson.fromJson(responseString, JsonObject.class);

            return jsonResponse.get("idToken").getAsString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}