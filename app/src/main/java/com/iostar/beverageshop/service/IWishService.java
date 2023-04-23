package com.iostar.beverageshop.service;

import com.iostar.beverageshop.model.WishItem;
import com.iostar.beverageshop.model.request.AddWishRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IWishService {

    @GET("client/check/wishitem")
    Call<Boolean> checkProductIsWishItem(@Query("productName") String productName, @Query("userId") Long userId);

    @POST("client/wishhandle")
    Call<WishItem> handleWishItem(@Body AddWishRequest wishRequest);

    @GET("client/wishitems/current")
    Call<List<WishItem>> getCurrentWishItemOfUser(@Query("userId") Long userId);
}
