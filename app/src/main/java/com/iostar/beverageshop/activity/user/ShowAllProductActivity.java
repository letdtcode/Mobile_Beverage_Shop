package com.iostar.beverageshop.activity.user;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iostar.beverageshop.adapter.user.ProductViewAllAdapter;
import com.iostar.beverageshop.databinding.ActivityShowAllProductBinding;
import com.iostar.beverageshop.model.Product;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.IProductService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowAllProductActivity extends AppCompatActivity {
    private ActivityShowAllProductBinding binding;
    private List<Product> productList;
    private ProductViewAllAdapter productViewAllAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowAllProductBinding.inflate(getLayoutInflater());
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
        binding.rvListProduct.setHasFixedSize(true);
        binding.rvListProduct.setLayoutManager(new LinearLayoutManager(ShowAllProductActivity.this, RecyclerView.VERTICAL, false));

        BaseAPIService.createService(IProductService.class).getInfoAllProductCurrentUse().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                productList = response.body();
                productViewAllAdapter = new ProductViewAllAdapter(productList, ShowAllProductActivity.this);
                binding.rvListProduct.setAdapter(productViewAllAdapter);
                if (productList == null || productList.size() == 0) {
                    binding.imgEmpty.setVisibility(View.VISIBLE);
                    binding.tvTitle.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("show_all_product", t.getMessage());
            }
        });

        binding.searchView.clearFocus();
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String nameProduct) {
                filterListProduct(nameProduct);
                return true;
            }
        });
    }

    private void filterListProduct(String nameProduct) {
        List<Product> filteredList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getProductName().toLowerCase().contains(nameProduct.toLowerCase())) {
                filteredList.add(product);
            }
        }
        if (nameProduct.equals("") && productList.size() == 0) {
            binding.tvNoProductSearch.setVisibility(View.GONE);
            binding.imgEmpty.setVisibility(View.VISIBLE);
            binding.tvTitle.setVisibility(View.VISIBLE);
            return;
        }
        if (filteredList.isEmpty()) {
            binding.tvNoProductSearch.setVisibility(View.VISIBLE);
            binding.imgEmpty.setVisibility(View.GONE);
            binding.tvTitle.setVisibility(View.GONE);
            productViewAllAdapter.setFilteredList(filteredList);
        } else {
            binding.tvNoProductSearch.setVisibility(View.GONE);
            productViewAllAdapter.setFilteredList(filteredList);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (productViewAllAdapter != null) {
            productViewAllAdapter.release();
        }
    }
}