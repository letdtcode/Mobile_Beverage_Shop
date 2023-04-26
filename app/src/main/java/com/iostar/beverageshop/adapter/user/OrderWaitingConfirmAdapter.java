package com.iostar.beverageshop.adapter.user;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iostar.beverageshop.databinding.ItemCategoryHomeBinding;
import com.iostar.beverageshop.databinding.ItemOrderWaitingConfirmBinding;
import com.iostar.beverageshop.model.Order;
import com.iostar.beverageshop.model.OrderItem;

import java.util.List;

public class OrderWaitingConfirmAdapter extends RecyclerView.Adapter<OrderWaitingConfirmAdapter.OrderWaitingConfirmViewHolder> {

    private final List<OrderItem> orderItems;

    public OrderWaitingConfirmAdapter(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
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
        OrderItem item = orderItems.get(position);
        String toppings = "";
        for (String name : item.getToppingsName()) {
            toppings = toppings + name + "\n";
        }
        holder.binding.tvTitle.setText(item.getProductName());
        holder.binding.tvToppings.setText(toppings);
        holder.binding.tvSizeProduct.setText(item.getSizeName());
        holder.binding.tvPrice.setText(item.getTotalPriceProduct().toString());
        holder.binding.tvTotalPayment.setText(item.getTotalPriceItem().toString());
        holder.binding.tvCount.setText(item.getQuantity().toString());
    }

    @Override
    public int getItemCount() {
        if (orderItems != null) {
            return orderItems.size();
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
