package com.iostar.beverageshop.service;

import com.iostar.beverageshop.model.Notification;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface INotificationService {
    //    CLient
    @GET("client/notifications")
    Call<List<Notification>> getAllNotifications();

    //    Staff
    @Multipart
    @POST("staff/notifications")
    Call<Notification> createNotification(@Part MultipartBody.Part file, @Part("model") RequestBody jsonBody);
}
