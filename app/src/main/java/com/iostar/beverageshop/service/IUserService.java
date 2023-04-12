package com.iostar.beverageshop.service;

import com.iostar.beverageshop.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IUserService {
    @GET("client/user")
    Call<User> getInfoUserByMail(@Query("mail") String mail);

    @GET("client/users")
    Call<User> getInfoUserById(@Query("id") Long id);
}
