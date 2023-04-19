package com.iostar.beverageshop.service;

import com.iostar.beverageshop.model.CartItem;
import com.iostar.beverageshop.model.request.AddCartRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ICartService {

    @POST("client/cart/addproduct")
    Call<CartItem> creatNewProductInCart(@Body AddCartRequest request);
}
