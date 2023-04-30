package com.iostar.beverageshop.fragment.user.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iostar.beverageshop.adapter.user.order.OrderWaitingDeliveryAdapter;
import com.iostar.beverageshop.databinding.FragmentOrderWaitingDeliveryBinding;
import com.iostar.beverageshop.model.Order;

import java.util.List;

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
        getDataOrderWaitingConfirm();
    }

    private void getDataOrderWaitingConfirm() {
        getParentFragmentManager().setFragmentResultListener("toOrderWaitingDelivery", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                orders = (List<Order>) result.getSerializable("orders_waiting_delivery");
                if (orders.size() > 0) {
                    adapter = new OrderWaitingDeliveryAdapter(orders, getActivity());
                    binding.rvOrderWaitingDelivery.setAdapter(adapter);
                } else {
                    binding.imgEmpty.setVisibility(View.VISIBLE);
                    binding.tvTitle.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.release();
        }
    }
}