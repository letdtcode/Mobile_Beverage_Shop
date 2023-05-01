package com.iostar.beverageshop.service;

import com.iostar.beverageshop.model.Order;
import com.iostar.beverageshop.model.OrderItem;
import com.iostar.beverageshop.model.request.CheckOutCartRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface IOrderService {
    @POST("client/order/checkout")
    Call<Order> checkOutInCart(@Body CheckOutCartRequest request);

    @GET("client/orderItems")
    Call<List<OrderItem>> getAllListOrderItems(@Query("orderId") Long orderId);

//    @GET("client/orders")
//    Call<List<Order>> getAllListOrderOfUser(@Query("userId") Long userId);

    @GET("client/orders/waiting-confirm")
    Call<List<Order>> getListOrderWaitingConfirmOfUser(@Query("userId") Long userId);

    @GET("client/orders/waiting-delivery")
    Call<List<Order>> getListOrderWaitingDeliveryOfUser(@Query("userId") Long userId);

    @GET("client/orders/success")
    Call<List<Order>> getListOrderSuccessOfUser(@Query("userId") Long userId);

    @GET("client/orders/cancel")
    Call<List<Order>> getListOrderCancelOfUser(@Query("userId") Long userId);

    @PUT("staff/order/approve")
    Call<Order> approveOrder(@Query("orderId") Long orderId, @Query("status") Integer status);
}
