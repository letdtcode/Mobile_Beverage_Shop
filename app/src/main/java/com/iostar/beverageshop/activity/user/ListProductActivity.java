package com.iostar.beverageshop.activity.user;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iostar.beverageshop.adapter.user.ProductByCategoryAdapter;
import com.iostar.beverageshop.databinding.ActivityListProductBinding;
import com.iostar.beverageshop.model.Product;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.IProductService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListProductActivity extends AppCompatActivity {
    private ActivityListProductBinding binding;
    private ProductByCategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rvListProduct.setHasFixedSize(true);
        binding.rvListProduct.setLayoutManager(new LinearLayoutManager(ListProductActivity.this, RecyclerView.VERTICAL, false));

        initial();
        setEvent();
    }

    private void setEvent() {
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initial() {
        String categoryName = getIntent().getStringExtra("category_name");
        BaseAPIService.createService(IProductService.class).getProductsByCategoryName(categoryName).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> productList = response.body();
                if (productList != null && productList.size() > 0) {
                    adapter = new ProductByCategoryAdapter(productList, ListProductActivity.this);
                    binding.rvListProduct.setAdapter(adapter);
                } else {
                    binding.imgEmpty.setVisibility(View.VISIBLE);
                    binding.tvTitle.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("error_products_cate", t.getMessage());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.release();
        }
    }
}