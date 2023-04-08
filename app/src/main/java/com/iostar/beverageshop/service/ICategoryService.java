package com.iostar.beverageshop.service;

import com.iostar.beverageshop.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ICategoryService {
    @GET("client/categories")
//    @Headers("Authorization:Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJyb2xlcyI6IltBZG1pbl0iLCJleHAiOjE2ODA5ODAwNDQsImlhdCI6MTY4MDk3OTQ0NH0.9Zgs1lvGoAhbnxTzHDMgsx4cT7fbTbc_qZ54Wd1yxP8")
    Call<List<Category>> getAllCategories();
}
