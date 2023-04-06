package com.iostar.beverageshop.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class BaseAPIService {
    private static final String BASE_URL = "http://10.0.2.2:8080/api/v1/";
    private static final OkHttpClient httpClient
            = new OkHttpClient
            .Builder()
            .build();
    private static final Retrofit.Builder builder
            = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create());

    private static final Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {

        return retrofit.create(serviceClass);
    }
}
