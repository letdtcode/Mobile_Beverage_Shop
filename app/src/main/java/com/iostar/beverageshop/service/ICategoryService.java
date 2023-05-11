package com.iostar.beverageshop.service;

import com.iostar.beverageshop.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ICategoryService {
    @GET("client/categories")
    Call<List<Category>> getAllCategories();
}
