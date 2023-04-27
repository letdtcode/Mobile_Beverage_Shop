package com.iostar.beverageshop.adapter.user.order_detail;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iostar.beverageshop.databinding.ItemOrderDetailWaitingConfirmBinding;
import com.iostar.beverageshop.model.OrderItem;

import java.util.List;

public class ODetailWaitingConfirmAdapter extends RecyclerView.Adapter<ODetailWaitingConfirmAdapter.OrderDetailWaitingConfirmViewHolder> {

    private final List<OrderItem> orderItems;

    public ODetailWaitingConfirmAdapter(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @NonNull
    @Override
    public OrderDetailWaitingConfirmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOrderDetailWaitingConfirmBinding binding = ItemOrderDetailWaitingConfirmBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ODetailWaitingConfirmAdapter.OrderDetailWaitingConfirmViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailWaitingConfirmViewHolder holder, int position) {
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

    public static class OrderDetailWaitingConfirmViewHolder extends RecyclerView.ViewHolder {
        private final ItemOrderDetailWaitingConfirmBinding binding;

        public OrderDetailWaitingConfirmViewHolder(ItemOrderDetailWaitingConfirmBinding itemOrderDetailWaitingConfirmBinding) {
            super(itemOrderDetailWaitingConfirmBinding.getRoot());
            binding = itemOrderDetailWaitingConfirmBinding;
        }
    }
}
