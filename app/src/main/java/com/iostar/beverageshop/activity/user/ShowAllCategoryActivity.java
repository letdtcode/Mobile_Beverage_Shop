package com.iostar.beverageshop.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.iostar.beverageshop.adapter.user.CategoryHomeAdapter;
import com.iostar.beverageshop.adapter.user.CategoyViewAllAdapter;
import com.iostar.beverageshop.databinding.ActivityShowAllCategoryBinding;
import com.iostar.beverageshop.model.Category;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.ICategoryService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowAllCategoryActivity extends AppCompatActivity {
    private ActivityShowAllCategoryBinding binding;
    private List<Category> categoryList;
    private CategoyViewAllAdapter categoyViewAllAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowAllCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
        binding.rcvCategoryViewAll.setHasFixedSize(true);
        binding.rcvCategoryViewAll.setLayoutManager(new GridLayoutManager(ShowAllCategoryActivity.this, 4));

        BaseAPIService.createService(ICategoryService.class).getAllCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                categoryList = response.body();
                if (categoryList != null && categoryList.size() != 0) {
                    categoyViewAllAdapter = new CategoyViewAllAdapter(categoryList, ShowAllCategoryActivity.this);
                    binding.rcvCategoryViewAll.setAdapter(categoyViewAllAdapter);
                    categoyViewAllAdapter.setOnItemClickListener(new CategoryHomeAdapter.OnItemCategoryClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            String categoryName = categoryList.get(position).getCategoryName();
                            Intent intent = new Intent(ShowAllCategoryActivity.this, ListProductActivity.class);
                            intent.putExtra("category_name", categoryName);
                            startActivity(intent);
                        }
                    });
                } else {
                    binding.imgEmpty.setVisibility(View.VISIBLE);
                    binding.tvTitle.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("err_get_categories", t.getMessage());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (categoyViewAllAdapter != null) {
            categoyViewAllAdapter.release();
        }
    }
}