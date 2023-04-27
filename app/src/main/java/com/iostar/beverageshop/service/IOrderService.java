package com.iostar.beverageshop.service;

import com.iostar.beverageshop.model.Order;
import com.iostar.beverageshop.model.OrderItem;
import com.iostar.beverageshop.model.request.CheckOutCartRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IOrderService {
    @POST("client/order/checkout")
    Call<Order> checkOutInCart(@Body CheckOutCartRequest request);

    @GET("client/orderItems")
    Call<List<OrderItem>> getAllListOrderItems(@Query("orderId") Long orderId);

    @GET("client/orders")
    Call<List<Order>> getAllListOrderOfUser(@Query("userId") Long userId);
}
