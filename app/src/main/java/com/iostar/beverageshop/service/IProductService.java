package com.iostar.beverageshop.service;

import com.iostar.beverageshop.model.Product;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface IProductService {

    //    Client
    @GET("client/products")
    Call<List<Product>> getInfoAllProductCurrentUse();

    @GET("client/image/product")
    Call<ResponseBody> getImgPathProductByProductName(@Query("productName") String productName);

    @GET("client/products-cate")
    Call<List<Product>> getProductsByCategoryName(@Query("categoryName") String categoryName);

    //    Staff
    @GET("staff/products")
    Call<List<Product>> getInfoAllProduct();

    @PUT("staff/product/changestatus")
    Call<Product> changeStatusOfProduct(@Query("productName") String productName, @Query("status") Integer status);
}
