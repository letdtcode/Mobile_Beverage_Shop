package com.iostar.beverageshop.storage;

import android.content.Context;

import com.google.gson.Gson;
import com.iostar.beverageshop.model.User;
import com.iostar.beverageshop.model.response.AuthResponse;

public class DataLocalManager {
    private static final String KEY_AUTH_TOKEN = "keyauthtoken";
    private static final String KEY_USER = "keyuser";
    private static DataLocalManager instance;
    private MySharedPreference mySharedPreference;

    public static void init(Context context) {
        instance = new DataLocalManager();
        instance.mySharedPreference = new MySharedPreference(context);
    }

    public static DataLocalManager getInstance() {
        if (instance == null) {
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void saveAuthToken(AuthResponse authResponse) {
        Gson gson = new Gson();
        String jsonAuthToken = gson.toJson(authResponse);
        DataLocalManager.getInstance().mySharedPreference.putStringValue(KEY_AUTH_TOKEN, jsonAuthToken);
    }

    public static AuthResponse getAuthToken() {
        String jsonAuthToken = DataLocalManager.getInstance().mySharedPreference.getStringValue(KEY_AUTH_TOKEN);
        Gson gson = new Gson();
        AuthResponse authResponse = gson.fromJson(jsonAuthToken, AuthResponse.class);
        return authResponse;
    }

    public static void saveUser(User user) {
        Gson gson = new Gson();
        String jsonUser = gson.toJson(user);
        DataLocalManager.getInstance().mySharedPreference.putStringValue(KEY_USER, jsonUser);
    }
    public static User getUser() {
        String jsonUser = DataLocalManager.getInstance().mySharedPreference.getStringValue(KEY_USER);
        Gson gson = new Gson();
        User user = gson.fromJson(jsonUser, User.class);
        return user;
    }

}
