package com.iostar.beverageshop.service;

import com.iostar.beverageshop.model.User;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IUserService {
    @GET("client/user")
    public Call<User> getInfoUserByMail();
}
