package com.iostar.beverageshop.service;

import com.iostar.beverageshop.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IProductService {
    @GET("client/products")
    Call<List<Product>> getInfoAllProduct();

//    @GET("client/images/products/{Id}")
//    Call<ResponseBody> getImageProduct(@Path("Id") Long id);
}
