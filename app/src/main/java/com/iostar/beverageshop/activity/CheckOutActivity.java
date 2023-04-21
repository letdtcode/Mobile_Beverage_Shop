package com.iostar.beverageshop.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iostar.beverageshop.adapter.CartAdapter;
import com.iostar.beverageshop.adapter.CheckOutAdapter;
import com.iostar.beverageshop.databinding.ActivityCheckOutBinding;
import com.iostar.beverageshop.model.CartItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CheckOutActivity extends AppCompatActivity {
    private ActivityCheckOutBinding binding;
    private List<String> payMent;

    private List<CartItem> cartItemsSelected;
    private List<String> pathImgProductSelected;

    private CheckOutAdapter checkOutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckOutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.rvOrder.setHasFixedSize(true);
        binding.rvOrder.setLayoutManager(new LinearLayoutManager(CheckOutActivity.this, RecyclerView.VERTICAL, false));

        initial();
        setEvent();
    }

    private void initial() {
//        Get Data From CartActivity
        Bundle bundle = getIntent().getExtras();
        String totalPriceStr = bundle.getString("total_price");
        cartItemsSelected = (List<CartItem>) bundle.getSerializable("list_cart_item");
        pathImgProductSelected = (List<String>) bundle.getSerializable("list_path_img");
        BigDecimal totalPrice = new BigDecimal(totalPriceStr);

//        Adapter for spinner
        payMent = new ArrayList<>();
        payMent.add("Giao hàng nhanh");
        payMent.add("Nhận lịch giao hàng");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(CheckOutActivity.this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, payMent);
        binding.spinnerMethodPayment.setAdapter(spinnerAdapter);

//        Set Adapter for Cart Item Checkout
        checkOutAdapter = new CheckOutAdapter(cartItemsSelected, pathImgProductSelected, CheckOutActivity.this);
        binding.rvOrder.setAdapter(checkOutAdapter);

    }

    private void setEvent() {
    }
}