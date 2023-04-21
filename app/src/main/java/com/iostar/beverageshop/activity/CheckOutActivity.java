package com.iostar.beverageshop.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iostar.beverageshop.adapter.CheckOutAdapter;
import com.iostar.beverageshop.databinding.ActivityCheckOutBinding;
import com.iostar.beverageshop.model.CartItem;
import com.iostar.beverageshop.storage.DataLocalManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CheckOutActivity extends AppCompatActivity {
    private ActivityCheckOutBinding binding;
    private List<String> payMent;

    private List<CartItem> cartItemsSelected;
    private List<String> pathImgProductSelected;

    private CheckOutAdapter checkOutAdapter;
    private Integer shipping = null;
    private BigDecimal totalPrice = null;

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
        totalPrice = new BigDecimal(totalPriceStr);

//        Adapter for spinner
        payMent = new ArrayList<>();
        payMent.add("Giao hàng nhanh");
        payMent.add("Nhận lịch giao hàng");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(CheckOutActivity.this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, payMent);
        binding.spinnerMethodPayment.setAdapter(spinnerAdapter);

//        Set Adapter for Cart Item Checkout
        checkOutAdapter = new CheckOutAdapter(cartItemsSelected, pathImgProductSelected, CheckOutActivity.this);
        binding.rvOrder.setAdapter(checkOutAdapter);

//        Set Up Data For Another View
        binding.edAddress.setText(DataLocalManager.getUser().getAddress());
        binding.tvSubtotalProduct.setText(totalPrice.toString());

//        Integer shipping = Integer.valueOf(binding.tvSubTotalDelivery.getText().toString());
//        Log.e("shipping", shipping.toString());
//        BigDecimal totalPay = totalPrice.add(BigDecimal.valueOf(shipping));
//        binding.tvTotalPayment.setText(totalPay.toString());
//        binding.tvTotalPrice.setText(totalPay.toString());
    }

    private void setUpTotalPay() {
        BigDecimal totalPay = totalPrice.add(BigDecimal.valueOf(shipping));
        binding.tvTotalPayment.setText(totalPay.toString());
        binding.tvTotalPrice.setText(totalPay.toString());
    }

    private void setEvent() {
        binding.spinnerMethodPayment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        shipping = 20000;
                        binding.tvSubTotalDelivery.setText(String.valueOf(shipping));
                        setUpTotalPay();
                        break;
                    case 1:
                        shipping = 15000;
                        binding.tvSubTotalDelivery.setText(String.valueOf(shipping));
                        setUpTotalPay();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}