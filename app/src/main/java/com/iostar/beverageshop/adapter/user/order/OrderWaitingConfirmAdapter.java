package com.iostar.beverageshop.adapter.user.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iostar.beverageshop.activity.user.OrderDetailActivity;
import com.iostar.beverageshop.activity.user.PersonalActivity;
import com.iostar.beverageshop.databinding.ItemOrderWaitingConfirmBinding;
import com.iostar.beverageshop.model.Order;
import com.iostar.beverageshop.model.OrderItem;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.IOrderService;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderWaitingConfirmAdapter extends RecyclerView.Adapter<OrderWaitingConfirmAdapter.OrderWaitingConfirmViewHolder> {

    private final List<Order> orders;
    private List<OrderItem> orderItemsOfOrderCLick = null;
    private Context mContext;

    public OrderWaitingConfirmAdapter(List<Order> orders, Context mContext) {
        this.orders = orders;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public OrderWaitingConfirmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOrderWaitingConfirmBinding binding = ItemOrderWaitingConfirmBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new OrderWaitingConfirmAdapter.OrderWaitingConfirmViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderWaitingConfirmViewHolder holder, int position) {
        Order item = orders.get(position);

        holder.binding.orderId.setText(item.getId().toString());
        holder.binding.tvNameCus.setText(item.getNameCustomer());
        holder.binding.tvAddress.setText(item.getAddress());
        holder.binding.tvPhoneNumber.setText(item.getPhoneNumber());
        holder.binding.tvTotalPriceOrder.setText(item.getTotalPrice().toString());

        holder.binding.tvDetailOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseAPIService.createService(IOrderService.class).getAllListOrderItems(item.getId()).enqueue(new Callback<List<OrderItem>>() {
                    @Override
                    public void onResponse(Call<List<OrderItem>> call, Response<List<OrderItem>> response) {
                        orderItemsOfOrderCLick = response.body();
                        if (orderItemsOfOrderCLick != null && orderItemsOfOrderCLick.size() > 0) {
                            Intent intent = new Intent(mContext, OrderDetailActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("order_detail_list", (Serializable) orderItemsOfOrderCLick);
                            bundle.putSerializable("order_current", item);
                            intent.putExtras(bundle);
                            mContext.startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<OrderItem>> call, Throwable t) {

                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        if (orders != null) {
            return orders.size();
        }
        return 0;
    }

    public static class OrderWaitingConfirmViewHolder extends RecyclerView.ViewHolder {
        private final ItemOrderWaitingConfirmBinding binding;

        public OrderWaitingConfirmViewHolder(ItemOrderWaitingConfirmBinding itemOrderWaitingConfirmBinding) {
            super(itemOrderWaitingConfirmBinding.getRoot());
            binding = itemOrderWaitingConfirmBinding;
        }
    }

    public void release() {
        mContext = null;
    }

}
