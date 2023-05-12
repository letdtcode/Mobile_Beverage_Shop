package com.iostar.beverageshop.service;

import com.google.gson.JsonObject;
import com.iostar.beverageshop.model.User;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IUserService {
//    @GET("client/user")
//    Call<User> getInfoUserByMail(@Query("mail") String mail);

    @GET("client/userId")
    Call<User> getInfoUserById(@Query("id") Long id);

    @PUT("client/user/{id}")
    Call<User> updateInfoUser(@Path("id") Long id, @Body JsonObject user);

    @Multipart
    @PUT("client/user/img/{id}")
    Call<User> updateImageUser(@Part MultipartBody.Part file, @Path("id") Long id);

    @PUT("client/user/changepass")
    Call<User> changePassword(@Query("mail") String mail, @Query("newPass") String newPass);
}
