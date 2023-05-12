package com.iostar.beverageshop.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.iostar.beverageshop.adapter.ViewPagerOrderAdapter;
import com.iostar.beverageshop.databinding.ActivityOrderBinding;
import com.iostar.beverageshop.fragment.user.order.OrderCanceledFragment;
import com.iostar.beverageshop.fragment.user.order.OrderSuccessFragment;
import com.iostar.beverageshop.fragment.user.order.OrderWaitingConfirmFragment;
import com.iostar.beverageshop.fragment.user.order.OrderWaitingDeliveryFragment;
import com.iostar.beverageshop.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    private ActivityOrderBinding binding;
    private ArrayList<Fragment> fragmentOrderDetail;
    private ViewPagerOrderAdapter viewPagerOrderAdapter;
    private List<Order> orders = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initial();
        setEvent();
    }

    private void setEvent() {
        binding.imgBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderActivity.this, CartActivity.class));
            }
        });
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initial() {
        fragmentOrderDetail = new ArrayList<>();
        fragmentOrderDetail.add(new OrderWaitingConfirmFragment());
        fragmentOrderDetail.add(new OrderWaitingDeliveryFragment());
        fragmentOrderDetail.add(new OrderSuccessFragment());
        fragmentOrderDetail.add(new OrderCanceledFragment());

        viewPagerOrderAdapter = new ViewPagerOrderAdapter(OrderActivity.this, fragmentOrderDetail);
        binding.viewPagerTabLayout.setAdapter(viewPagerOrderAdapter);
        new TabLayoutMediator(binding.tabLayout, binding.viewPagerTabLayout, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Chờ xác nhận");
                        break;
                    case 1:
                        tab.setText("Đang giao");
                        break;
                    case 2:
                        tab.setText("Đã nhận");
                        break;
                    case 3:
                        tab.setText("Đã hủy");
                        break;
                }
            }
        }).attach();
    }
}