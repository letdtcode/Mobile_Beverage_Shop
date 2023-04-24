package com.iostar.beverageshop.fragment.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.iostar.beverageshop.activity.user.CartActivity;
import com.iostar.beverageshop.adapter.ViewPagerOrderAdapter;
import com.iostar.beverageshop.databinding.FragmentHomeBinding;
import com.iostar.beverageshop.databinding.FragmentOrderBinding;
import com.iostar.beverageshop.fragment.user.order.OrderCanceledFragment;
import com.iostar.beverageshop.fragment.user.order.OrderSuccessFragment;
import com.iostar.beverageshop.fragment.user.order.OrderWaitingConfirmFragment;
import com.iostar.beverageshop.fragment.user.order.OrderWaitingDeliveryFragment;
import com.iostar.beverageshop.model.Order;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.IOrderService;
import com.iostar.beverageshop.storage.DataLocalManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderFragment extends Fragment {
    private FragmentOrderBinding binding;
    private ArrayList<Fragment> fragmentOrderDetail;
    private ViewPagerOrderAdapter viewPagerOrderAdapter;
    private List<Order> orderList = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initial();
        setUpData();
        setEvent();
    }

    private void setUpData() {
        Long userId = DataLocalManager.getUser().getId();
        BaseAPIService.createService(IOrderService.class).getAllListOrder(userId).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                orderList = response.body();
                if (orderList != null && orderList.size() > 0) {

                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {

            }
        });
    }

    private void initial() {
        fragmentOrderDetail = new ArrayList<>();
        fragmentOrderDetail.add(new OrderWaitingConfirmFragment());
        fragmentOrderDetail.add(new OrderWaitingDeliveryFragment());
        fragmentOrderDetail.add(new OrderSuccessFragment());
        fragmentOrderDetail.add(new OrderCanceledFragment());

        viewPagerOrderAdapter = new ViewPagerOrderAdapter(getActivity(), fragmentOrderDetail);
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

    private void setEvent() {
        binding.imgBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CartActivity.class));
            }
        });
    }
}