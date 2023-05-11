package com.iostar.beverageshop.adapter.staff.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iostar.beverageshop.activity.user.OrderDetailActivity;
import com.iostar.beverageshop.databinding.ItemOrderWaitingConfirmStaffBinding;
import com.iostar.beverageshop.inteface.IOnApproveOrderClickListener;
import com.iostar.beverageshop.model.Order;
import com.iostar.beverageshop.model.OrderItem;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.IOrderService;
import com.iostar.beverageshop.utils.ToastUtils;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderWaitingConfirmStaffAdapter extends RecyclerView.Adapter<OrderWaitingConfirmStaffAdapter.OrderWaitingConfirmStaffViewHolder> {

//    private final List<Order> orders;
//    private List<OrderItem> orderItemsOfOrderCLick = null;
//    private Context mContext;
//    private IOnApproveOrderClickListener onApproveOrderClickListener;
//
//    public OrderWaitingConfirmStaffAdapter(List<Order> orders, Context mContext, IOnApproveOrderClickListener onApproveOrderClickListener) {
//        this.orders = orders;
//        this.mContext = mContext;
//        this.onApproveOrderClickListener = onApproveOrderClickListener;
//    }
//
//    @NonNull
//    @Override
//    public OrderWaitingConfirmStaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        ItemOrderWaitingConfirmStaffBinding binding = ItemOrderWaitingConfirmStaffBinding.inflate(
//                LayoutInflater.from(parent.getContext()),
//                parent,
//                false
//        );
//        return new OrderWaitingConfirmStaffAdapter.OrderWaitingConfirmStaffViewHolder(binding);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull OrderWaitingConfirmStaffViewHolder holder, int position) {
//        Order item = orders.get(position);
//
//        holder.binding.orderId.setText(item.getId().toString());
//        holder.binding.tvNameCus.setText(item.getNameCustomer());
//        holder.binding.tvAddress.setText(item.getAddress());
//        holder.binding.tvPhoneNumber.setText(item.getPhoneNumber());
//        holder.binding.tvTotalPriceOrder.setText(item.getTotalPrice().toString());
//
//        holder.binding.tvDetailOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BaseAPIService.createService(IOrderService.class).getAllListOrderItems(item.getId()).enqueue(new Callback<List<OrderItem>>() {
//                    @Override
//                    public void onResponse(Call<List<OrderItem>> call, Response<List<OrderItem>> response) {
//                        orderItemsOfOrderCLick = response.body();
//                        if (orderItemsOfOrderCLick != null && orderItemsOfOrderCLick.size() > 0) {
//                            Intent intent = new Intent(mContext, OrderDetailActivity.class);
//                            Bundle bundle = new Bundle();
//                            bundle.putSerializable("order_detail_list", (Serializable) orderItemsOfOrderCLick);
//                            bundle.putSerializable("order_current", item);
//                            intent.putExtras(bundle);
//                            mContext.startActivity(intent);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<OrderItem>> call, Throwable t) {
//                        Log.e("err", t.getMessage());
//                    }
//                });
//            }
//        });
//        holder.binding.btnToDelivery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Long orderId = Long.parseLong(holder.binding.orderId.getText().toString());
//                BaseAPIService.createService(IOrderService.class).approveOrder(orderId, 2).enqueue(new Callback<Order>() {
//                    @Override
//                    public void onResponse(Call<Order> call, Response<Order> response) {
//                        ToastUtils.showToastCustom(mContext, "Duyệt thành công !");
//                    }
//
//                    @Override
//                    public void onFailure(Call<Order> call, Throwable t) {
//                        Log.e("acceptToDelivery", t.getMessage());
//                    }
//                });
//                orders.remove(holder.getAdapterPosition());
//                notifyItemRemoved(holder.getAdapterPosition());
//                onApproveOrderClickListener.onOrderClick();
//            }
//        });
//
//        holder.binding.btnToCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Long orderId = Long.parseLong(holder.binding.orderId.getText().toString());
//                BaseAPIService.createService(IOrderService.class).approveOrder(orderId, 0).enqueue(new Callback<Order>() {
//                    @Override
//                    public void onResponse(Call<Order> call, Response<Order> response) {
//                        ToastUtils.showToastCustom(mContext, "Đã hủy đơn hàng !");
//                    }
//
//                    @Override
//                    public void onFailure(Call<Order> call, Throwable t) {
//                        Log.e("canceled_order", t.getMessage());
//                    }
//                });
//                orders.remove(holder.getAdapterPosition());
//                notifyItemRemoved(holder.getAdapterPosition());
//                onApproveOrderClickListener.onOrderClick();
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        if (orders != null) {
//            return orders.size();
//        }
//        return 0;
//    }
//
//    public static class OrderWaitingConfirmStaffViewHolder extends RecyclerView.ViewHolder {
//        private final ItemOrderWaitingConfirmStaffBinding binding;
//
//        public OrderWaitingConfirmStaffViewHolder(ItemOrderWaitingConfirmStaffBinding itemOrderWaitingConfirmStaffBinding) {
//            super(itemOrderWaitingConfirmStaffBinding.getRoot());
//            binding = itemOrderWaitingConfirmStaffBinding;
//        }
//    }
//
//    public void release() {
//        mContext = null;
//    }
}
