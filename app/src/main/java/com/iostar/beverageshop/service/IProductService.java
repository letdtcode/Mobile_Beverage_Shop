package com.iostar.beverageshop.service;

import com.iostar.beverageshop.model.Category;
import com.iostar.beverageshop.model.Product;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IProductService {
    @GET("client/products")
    Call<List<Product>> getInfoAllProduct();

//    @GET("client/images/products/{Id}")
//    Call<ResponseBody> getImageProduct(@Path("Id") Long id);
}
