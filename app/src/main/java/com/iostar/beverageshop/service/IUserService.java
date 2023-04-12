package com.iostar.beverageshop.service;

import com.google.gson.JsonObject;
import com.iostar.beverageshop.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IUserService {
//    @GET("client/user")
//    Call<User> getInfoUserByMail(@Query("mail") String mail);

    @GET("client/users")
    Call<User> getInfoUserById(@Query("id") Long id);

    @PUT("client/user{id}")
    Call<User> updateInfoUser(@Path("id") Long id, @Body JsonObject user);

    @PUT("client/user/img/{id}")
    Call<User> updateImageUser(@Query("file") Multipart file, @Path("id") Long id);
}
