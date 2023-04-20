package com.iostar.beverageshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.iostar.beverageshop.R;
import com.iostar.beverageshop.adapter.CartAdapter;
import com.iostar.beverageshop.adapter.ProductHomeAdapter;
import com.iostar.beverageshop.adapter.ToppingDetailAdapter;
import com.iostar.beverageshop.databinding.ActivityCartBinding;
import com.iostar.beverageshop.databinding.ActivityMainBinding;
import com.iostar.beverageshop.model.CartItem;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.ICartService;
import com.iostar.beverageshop.storage.DataLocalManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    private ActivityCartBinding binding;
    private List<CartItem> cartItemList;
    private CartAdapter cartAdapter;
    private Integer totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rvBasket.setHasFixedSize(true);
        binding.rvBasket.setLayoutManager(new LinearLayoutManager(CartActivity.this, RecyclerView.VERTICAL, false));

        showCartItem();
        setEvent();
    }

    private void showCartItem() {
        Long userId = DataLocalManager.getUser().getId();
        BaseAPIService.createService(ICartService.class).getAllCartItemInfo(userId).enqueue(new Callback<List<CartItem>>() {
            @Override
            public void onResponse(Call<List<CartItem>> call, Response<List<CartItem>> response) {
                cartItemList = response.body();
                if (cartItemList != null) {
                    for (CartItem item : cartItemList) {
                        totalPrice = totalPrice + item.getTotalPriceItem().intValue();
                    }
                    binding.tvTotalPrice.setText(totalPrice.toString());
                    cartAdapter = new CartAdapter(cartItemList);
                    binding.rvBasket.setAdapter(cartAdapter);
                } else {
                    binding.imgEmpty.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<CartItem>> call, Throwable t) {
                Log.e("error_api", t.getMessage());
            }
        });
    }

    private void setEvent() {
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, MainActivity.class));
            }
        });
        binding.btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, CheckOutActivity.class));
            }
        });
    }
}