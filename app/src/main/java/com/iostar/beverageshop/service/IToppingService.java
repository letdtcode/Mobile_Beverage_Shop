package com.iostar.beverageshop.service;

import com.iostar.beverageshop.model.Size;
import com.iostar.beverageshop.model.Topping;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IToppingService {
    @GET("client/toppings")
    Call<List<Topping>> getInfoToppingInfo();
}
