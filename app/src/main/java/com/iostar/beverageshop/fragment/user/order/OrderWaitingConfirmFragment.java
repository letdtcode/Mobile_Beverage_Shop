package com.iostar.beverageshop.fragment.user.order;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iostar.beverageshop.R;
import com.iostar.beverageshop.databinding.FragmentOrderBinding;
import com.iostar.beverageshop.databinding.FragmentOrderWaitingConfirmBinding;
import com.iostar.beverageshop.model.Order;

import java.util.List;


public class OrderWaitingConfirmFragment extends Fragment {

    private FragmentOrderWaitingConfirmBinding binding;
    private List<Order> orderList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrderWaitingConfirmBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDataOrderWaitingConfirm();
    }

    private void getDataOrderWaitingConfirm() {
        getParentFragmentManager().setFragmentResultListener("toOrderWaitingConfirm", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                orderList = (List<Order>) result.getSerializable("orders_waiting_confirm");
                if (orderList.size() > 0) {

                }
            }
        });
    }
}