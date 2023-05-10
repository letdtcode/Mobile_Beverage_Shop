package com.iostar.beverageshop.fragment.user.order;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iostar.beverageshop.adapter.user.order.OrderWaitingDeliveryAdapter;
import com.iostar.beverageshop.databinding.FragmentOrderWaitingDeliveryBinding;
import com.iostar.beverageshop.model.Order;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.IOrderService;
import com.iostar.beverageshop.storage.DataLocalManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderWaitingDeliveryFragment extends Fragment {
    private FragmentOrderWaitingDeliveryBinding binding;
    private List<Order> orders;
    private OrderWaitingDeliveryAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrderWaitingDeliveryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rvOrderWaitingDelivery.setHasFixedSize(true);
        binding.rvOrderWaitingDelivery.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        getDataOrderWaitingDelivery();
    }

    private void getDataOrderWaitingDelivery() {
        Long userId = DataLocalManager.getUser().getId();
        BaseAPIService.createService(IOrderService.class).getListOrderWaitingDeliveryOfUser(userId).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                orders = response.body();
                if (orders != null && orders.size() > 0) {
                    adapter = new OrderWaitingDeliveryAdapter(orders, getActivity());
                    binding.rvOrderWaitingDelivery.setAdapter(adapter);
                } else {
                    binding.imgEmpty.setVisibility(View.VISIBLE);
                    binding.tvTitle.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.e("orders_waiting_confirm", t.getMessage());
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.imgEmpty.setVisibility(View.INVISIBLE);
        binding.tvTitle.setVisibility(View.INVISIBLE);
        if (adapter != null) {
            adapter.release();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataOrderWaitingDelivery();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.release();
        }
    }
}