package com.iostar.beverageshop.fragment.user.order;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iostar.beverageshop.R;
import com.iostar.beverageshop.adapter.user.order.OrderSuccessAdapter;
import com.iostar.beverageshop.adapter.user.order.OrderWaitingConfirmAdapter;
import com.iostar.beverageshop.databinding.FragmentOrderSuccessBinding;
import com.iostar.beverageshop.databinding.FragmentOrderWaitingConfirmBinding;
import com.iostar.beverageshop.model.Order;

import java.util.List;

public class OrderSuccessFragment extends Fragment {

    private FragmentOrderSuccessBinding binding;
    private List<Order> orders;
    private OrderSuccessAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrderSuccessBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rvOrderSuccess.setHasFixedSize(true);
        binding.rvOrderSuccess.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        getDataOrderSuccess();
    }

    private void getDataOrderSuccess() {
        getParentFragmentManager().setFragmentResultListener("toOrderSuccess", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                orders = (List<Order>) result.getSerializable("orders_success");
                if (orders.size() > 0) {
                    adapter = new OrderSuccessAdapter(orders, getActivity());
                    binding.rvOrderSuccess.setAdapter(adapter);
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