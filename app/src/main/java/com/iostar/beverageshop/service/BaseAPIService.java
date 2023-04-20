package com.iostar.beverageshop.service;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseAPIService {
    private static final String BASE_URL = "http://10.0.2.2:8080/api/v1/";

    private static final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    static Authenticator interceptor = new TokenAuthenticator();
    private static final OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder()
                    .authenticator(interceptor)
                    .addInterceptor(loggingInterceptor);

    private static final Retrofit.Builder builder
            = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create());


    private static final Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {

        return retrofit.create(serviceClass);
    }
}
