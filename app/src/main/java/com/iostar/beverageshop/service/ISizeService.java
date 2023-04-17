package com.iostar.beverageshop.service;

import com.iostar.beverageshop.model.Size;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ISizeService {

    @GET("client/sizes")
    Call<List<Size>> getInfoUserById();
}
