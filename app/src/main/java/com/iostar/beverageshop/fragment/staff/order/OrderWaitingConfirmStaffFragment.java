package com.iostar.beverageshop.fragment.staff.order;

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

import com.iostar.beverageshop.adapter.staff.order.OrderWaitingConfirmStaffAdapter;
import com.iostar.beverageshop.databinding.FragmentOrderWaitingConfirmStaffBinding;
import com.iostar.beverageshop.inteface.IOnApproveOrderClickListener;
import com.iostar.beverageshop.model.Order;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.IOrderService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderWaitingConfirmStaffFragment extends Fragment implements IOnApproveOrderClickListener {
//    private FragmentOrderWaitingConfirmStaffBinding binding;
//    private List<Order> orders;
//    private OrderWaitingConfirmStaffAdapter adapter;
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        binding = FragmentOrderWaitingConfirmStaffBinding.inflate(inflater, container, false);
//        return binding.getRoot();
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        binding.rvOrderWaitingConfirm.setHasFixedSize(true);
//        binding.rvOrderWaitingConfirm.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
//        getDataOrderWaitingConfirm();
//    }
//
//    private void setDataAdapter() {
//        adapter = new OrderWaitingConfirmStaffAdapter(orders, getActivity(), this);
//        binding.rvOrderWaitingConfirm.setAdapter(adapter);
//    }
//
//    private void getDataOrderWaitingConfirm() {
//        BaseAPIService.createService(IOrderService.class).getListOrderByStatus(1).enqueue(new Callback<List<Order>>() {
//            @Override
//            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
//                orders = response.body();
//                if (orders != null && orders.size() > 0) {
//                    setDataAdapter();
//                } else {
//                    binding.imgEmpty.setVisibility(View.VISIBLE);
//                    binding.tvTitle.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Order>> call, Throwable t) {
//                Log.e("orders_waiting_confirm", t.getMessage());
//            }
//        });
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if (adapter != null) {
//            adapter.release();
//        }
//    }
//
//    @Override
//    public void onOrderClick() {
//        if (orders.size() == 0 || orders == null) {
//            binding.imgEmpty.setVisibility(View.VISIBLE);
//            binding.tvTitle.setVisibility(View.VISIBLE);
//        }
//    }
}
