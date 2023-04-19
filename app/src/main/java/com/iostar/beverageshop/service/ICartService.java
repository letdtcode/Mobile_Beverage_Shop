package com.iostar.beverageshop.service;

import com.iostar.beverageshop.model.CartItem;
import com.iostar.beverageshop.model.request.AddCartRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ICartService {

    @POST("client/cart/addproduct")
    Call<CartItem> creatNewProductInCart(@Body AddCartRequest request);

    @GET("client/cart/items")
    Call<List<CartItem>> getAllCartItemInfo(@Query("userId") Long userId);
}
