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

import com.iostar.beverageshop.adapter.user.order.OrderWaitingConfirmAdapter;
import com.iostar.beverageshop.databinding.FragmentOrderWaitingConfirmBinding;
import com.iostar.beverageshop.model.Order;

import java.util.List;


public class OrderWaitingConfirmFragment extends Fragment {

    private FragmentOrderWaitingConfirmBinding binding;
    private List<Order> orders;
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
                orders = (List<Order>) result.getSerializable("orders_waiting_confirm");
                if (orders.size() > 0) {
                    adapter = new OrderWaitingConfirmAdapter(orders, getActivity());
                    binding.rvOrderWaitingConfirm.setAdapter(adapter);
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