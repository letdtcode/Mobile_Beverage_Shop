package com.iostar.beverageshop.service;

import com.iostar.beverageshop.model.CartItem;
import com.iostar.beverageshop.model.Order;
import com.iostar.beverageshop.model.request.CheckOutCartRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IOrderService {
    @POST("client/order/checkout")
    Call<Order> checkOutInCart(@Body CheckOutCartRequest request);
}
