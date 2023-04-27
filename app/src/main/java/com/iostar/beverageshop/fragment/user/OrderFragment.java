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
import com.iostar.beverageshop.activity.user.CheckOutActivity;
import com.iostar.beverageshop.adapter.ViewPagerOrderAdapter;
import com.iostar.beverageshop.databinding.FragmentHomeBinding;
import com.iostar.beverageshop.databinding.FragmentOrderBinding;
import com.iostar.beverageshop.fragment.user.order.OrderCanceledFragment;
import com.iostar.beverageshop.fragment.user.order.OrderSuccessFragment;
import com.iostar.beverageshop.fragment.user.order.OrderWaitingConfirmFragment;
import com.iostar.beverageshop.fragment.user.order.OrderWaitingDeliveryFragment;
import com.iostar.beverageshop.model.Order;
import com.iostar.beverageshop.model.OrderItem;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.IOrderService;
import com.iostar.beverageshop.storage.DataLocalManager;

import java.io.Serializable;
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
    private List<Order> orders = null;

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
        BaseAPIService.createService(IOrderService.class).getAllListOrderOfUser(userId).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                orders = response.body();
                if (orders != null && orders.size() > 0) {
                    sendDataToOrderWaitingConfirm();
//                    sendDataToOrderWaitingDelivery();
//                    sendDataToOrderSuccess();
//                    sendDataToOrderCancel();
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {

            }
        });
    }

    private void sendDataToOrderCancel() {
        List<Order> orderListCancel = new ArrayList();
        for (Order item : orders) {
            if (item.getStatus() == 0) {
                orderListCancel.add(item);
            }
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("orders_cancel", (Serializable) orderListCancel);
        getParentFragmentManager().setFragmentResult("toOrderCancel", bundle);
    }

    private void sendDataToOrderSuccess() {
        List<Order> orderListSuccess = new ArrayList();
        for (Order item : orders) {
            if (item.getStatus() == 3) {
                orderListSuccess.add(item);
            }
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("orders_success", (Serializable) orderListSuccess);
        getParentFragmentManager().setFragmentResult("toOrderSuccess", bundle);
    }

    private void sendDataToOrderWaitingDelivery() {
        List<Order> orderListWaitingDelivery = new ArrayList();
        for (Order item : orders) {
            if (item.getStatus() == 2) {
                orderListWaitingDelivery.add(item);
            }
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("orders_waiting_delivery", (Serializable) orderListWaitingDelivery);
        getParentFragmentManager().setFragmentResult("toOrderWaitingDelivery", bundle);
    }

    private void sendDataToOrderWaitingConfirm() {
        List<Order> orderListWaitingConfirm = new ArrayList();
        for (Order item : orders) {
            if (item.getStatus() == 1) {
                orderListWaitingConfirm.add(item);
            }
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("orders_waiting_confirm", (Serializable) orderListWaitingConfirm);
        getParentFragmentManager().setFragmentResult("toOrderWaitingConfirm", bundle);
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