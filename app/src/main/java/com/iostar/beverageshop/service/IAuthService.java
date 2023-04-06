package com.iostar.beverageshop.service;

import com.google.gson.JsonObject;
import com.iostar.beverageshop.model.response.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAuthService {
    @POST("auth/authenticate")
    Call<AuthResponse> login(@Body JsonObject params);

    @POST("auth/register")
    Call<AuthResponse> register(@Body JsonObject params);

    @POST("auth/refresh")
    Call<AuthResponse> refreshToken(@Body JsonObject params);
}
