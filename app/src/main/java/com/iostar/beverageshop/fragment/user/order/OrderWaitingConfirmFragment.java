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

import com.iostar.beverageshop.adapter.user.OrderWaitingConfirmAdapter;
import com.iostar.beverageshop.databinding.FragmentOrderWaitingConfirmBinding;
import com.iostar.beverageshop.model.OrderItem;

import java.util.List;


public class OrderWaitingConfirmFragment extends Fragment {

    private FragmentOrderWaitingConfirmBinding binding;
    private List<OrderItem> orderItems;
    private OrderWaitingConfirmAdapter adapter;

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
        binding.rvOrderWaitingConfirm.setHasFixedSize(true);
        binding.rvOrderWaitingConfirm.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        getDataOrderWaitingConfirm();
    }

    private void getDataOrderWaitingConfirm() {
        getParentFragmentManager().setFragmentResultListener("toOrderWaitingConfirm", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                orderItems = (List<OrderItem>) result.getSerializable("orders_waiting_confirm");
                if (orderItems.size() > 0) {
                    adapter = new OrderWaitingConfirmAdapter(orderItems);
                    binding.rvOrderWaitingConfirm.setAdapter(adapter);
                }
            }
        });
    }
}