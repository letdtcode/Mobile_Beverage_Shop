package com.iostar.beverageshop.service;

import android.util.Log;

import com.iostar.beverageshop.model.response.AuthResponse;
import com.iostar.beverageshop.storage.DataLocalManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        AuthResponse authResponse = DataLocalManager.getAuthToken();
        Request.Builder builder = request.newBuilder();
        if (authResponse != null) {
            builder.addHeader("Authorization", "Bearer " + authResponse.getAccessToken());
            builder.addHeader("Content-Type","application/json;charset=UTF-8");
        }
        Log.d("REQUEST", " '" + authResponse.getAccessToken() + "'");
        return chain.proceed(request);
    }
}
