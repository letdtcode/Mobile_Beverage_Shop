package com.iostar.beverageshop.service;

import com.iostar.beverageshop.model.Notification;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface INotificationService {
    @GET("client/notifications")
    Call<List<Notification>> getAllNotifications();
}
