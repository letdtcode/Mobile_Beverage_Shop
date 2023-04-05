package com.iostar.beverageshop.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IApiService {
    //    Link api: http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
    //    https://jsonplaceholder.typicode.com/posts
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    IApiService apiService = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IApiService.class);

    @GET("api/live")
    Call<Currency> covertUsdToVnd(@Query("access_key") String access_key,
                                  @Query("currencies") String currencies,
                                  @Query("source") String source,
                                  @Query("format") int format);

    @POST("/posts")
    Call<Post> sendPosts(@Body Post post);
}
