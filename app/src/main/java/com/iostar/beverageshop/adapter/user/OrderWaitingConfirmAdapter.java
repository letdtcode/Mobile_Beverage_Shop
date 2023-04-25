package com.iostar.beverageshop.adapter.user;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iostar.beverageshop.databinding.ItemCategoryHomeBinding;
import com.iostar.beverageshop.databinding.ItemOrderWaitingConfirmBinding;
import com.iostar.beverageshop.model.Order;

import java.util.List;

public class OrderWaitingConfirmAdapter extends RecyclerView.Adapter<OrderWaitingConfirmAdapter.OrderWaitingConfirmViewHolder> {

    private final List<Order> orderList;

    public OrderWaitingConfirmAdapter(List<Order> orderList) {
        this.orderList = orderList;
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

    }

    @Override
    public int getItemCount() {
        if (orderList != null) {
            return orderList.size();
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

}
