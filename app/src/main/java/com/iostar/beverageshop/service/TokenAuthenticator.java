package com.iostar.beverageshop.service;

import com.iostar.beverageshop.storage.DataLocalManager;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class TokenAuthenticator implements Authenticator {
    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        String tokenAuth = DataLocalManager.getAuthToken().getAccessToken();

        return response.request().newBuilder()
                .header("Authorization", "Bearer " + tokenAuth)
                .build();
    }
}
