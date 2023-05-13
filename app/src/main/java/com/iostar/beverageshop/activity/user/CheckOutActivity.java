package com.iostar.beverageshop.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iostar.beverageshop.adapter.user.CheckOutAdapter;
import com.iostar.beverageshop.databinding.ActivityCheckOutBinding;
import com.iostar.beverageshop.model.CartItem;
import com.iostar.beverageshop.model.Order;
import com.iostar.beverageshop.model.request.CheckOutCartRequest;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.IOrderService;
import com.iostar.beverageshop.storage.DataLocalManager;
import com.iostar.beverageshop.utils.ToastUtils;
import com.iostar.beverageshop.utils.constants.DELIVERY_METHOD;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckOutActivity extends AppCompatActivity {
    private ActivityCheckOutBinding binding;
    private List<String> namePayMent;

    private List<CartItem> cartItemsSelected;
    private List<String> pathImgProductSelected;

    private CheckOutAdapter checkOutAdapter;
    private Integer shipping = null;
    private BigDecimal totalPrice = null;
    private int payMent = 0;

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
        namePayMent = new ArrayList<>();
        namePayMent.add(DELIVERY_METHOD.FastShipping);
        namePayMent.add(DELIVERY_METHOD.GetSchedule);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(CheckOutActivity.this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, namePayMent);
        binding.spinnerMethodPayment.setAdapter(spinnerAdapter);

//        Set Adapter for Cart Item Checkout
        checkOutAdapter = new CheckOutAdapter(cartItemsSelected, pathImgProductSelected, CheckOutActivity.this);
        binding.rvOrder.setAdapter(checkOutAdapter);

//        Set Up Data For Another View
        binding.edAddress.setText(DataLocalManager.getUser().getAddress());
        binding.tvSubtotalProduct.setText(totalPrice.toString());
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
                        payMent = 0;
                        binding.tvSubTotalDelivery.setText(String.valueOf(shipping));
                        setUpTotalPay();
                        break;
                    case 1:
                        shipping = 15000;
                        payMent = 1;
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

        binding.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = binding.edAddress.getText().toString();
                String nameCus = binding.etNameCus.getText().toString();
                String phoneNumber = binding.etPhoneNumber.getText().toString();
                List<Long> cartItemId = new ArrayList<>();
                for (CartItem itemSelected : cartItemsSelected) {
                    cartItemId.add(itemSelected.getId());
                }
                CheckOutCartRequest request = new CheckOutCartRequest(
                        DataLocalManager.getUser().getId(),
                        address,
                        nameCus,
                        payMent,
                        phoneNumber,
                        shipping,
                        cartItemId);

                BaseAPIService.createService(IOrderService.class).checkOutInCart(request).enqueue(new Callback<Order>() {
                    @Override
                    public void onResponse(Call<Order> call, Response<Order> response) {
                        Order order = response.body();
                        if (order != null) {
                            ToastUtils.showToastCustom(CheckOutActivity.this, "Đơn hàng được đặt thành công !");
                            startActivity(new Intent(CheckOutActivity.this, MainActivity.class));
                        }
                    }

                    @Override
                    public void onFailure(Call<Order> call, Throwable t) {
                        Log.e("api_checkout", t.getMessage());
                    }
                });
            }
        });
    }
}