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
import com.iostar.beverageshop.inteface.IOnCartItemCheckedListener;
import com.iostar.beverageshop.model.CartItem;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.ICartService;
import com.iostar.beverageshop.storage.DataLocalManager;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements IOnCartItemCheckedListener {
    private ActivityCartBinding binding;
    private List<CartItem> cartItemList;
    private CartAdapter cartAdapter;

    private BigDecimal totalPrice = BigDecimal.valueOf(0);

    private List<CartItem> cartItemsSelected;
    private List<String> pathImgProductSelected;

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
                if (cartItemList.size() != 0) {
                    IOnCartItemCheckedListener listener = CartActivity.this;
                    cartAdapter = new CartAdapter(cartItemList, CartActivity.this,listener);
                    binding.rvBasket.setAdapter(cartAdapter);
                } else {
                    binding.imgEmpty.setVisibility(View.VISIBLE);
                    binding.tvTitle.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<CartItem>> call, Throwable t) {
                Log.e("error_api", t.getMessage());
            }
        });
        cartItemsSelected = new ArrayList<>();
        pathImgProductSelected = new ArrayList<>();
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
                Intent intent = new Intent(CartActivity.this, CheckOutActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("list_cart_item", (Serializable) cartItemsSelected);
                bundle.putSerializable("list_path_img", (Serializable) pathImgProductSelected);
                bundle.putString("total_price", totalPrice.toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onChecked(CartItem cartItem, String pathImage) {
        totalPrice = totalPrice.add(cartItem.getTotalPriceItem());
        cartItemsSelected.add(cartItem);
        pathImgProductSelected.add(pathImage);
        binding.tvTotalPrice.setText(totalPrice.toString());
    }

    @Override
    public void onUnchecked(CartItem cartItem, String pathImage) {
        totalPrice = totalPrice.subtract(cartItem.getTotalPriceItem());
        cartItemsSelected.remove(cartItem);
        pathImgProductSelected.remove(pathImage);
        binding.tvTotalPrice.setText(totalPrice.toString());
    }
}