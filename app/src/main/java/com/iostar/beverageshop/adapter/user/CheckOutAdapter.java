package com.iostar.beverageshop.adapter.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iostar.beverageshop.databinding.ItemProductCheckoutBinding;
import com.iostar.beverageshop.model.CartItem;

import java.util.List;

public class CheckOutAdapter extends RecyclerView.Adapter<CheckOutAdapter.CheckOutViewHolder> {
    private final List<CartItem> cartItemsSelected;
    private List<String> pathImgProductSelected;
    private Context context;

    public CheckOutAdapter(List<CartItem> cartItemsSelected, List<String> pathImgProductSelected, Context context) {
        this.cartItemsSelected = cartItemsSelected;
        this.pathImgProductSelected = pathImgProductSelected;
        this.context = context;
    }

    @NonNull
    @Override
    public CheckOutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductCheckoutBinding binding = ItemProductCheckoutBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new CheckOutAdapter.CheckOutViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckOutViewHolder holder, int position) {
        CartItem cartItem = cartItemsSelected.get(position);
        String pathImg = pathImgProductSelected.get(position);
        if (cartItem == null || pathImg == null) {
            return;
        }
        String toppingList = "";
        for (String topping : cartItem.getToppingName()) {
            toppingList = toppingList + topping + " ";
        }
        Glide.with(context).load(pathImg).into(holder.binding.imgProductCheckout);
        holder.binding.tvProductCheckout.setText(cartItem.getProductName());
        holder.binding.tvToppingsCheckout.setText(toppingList);
        holder.binding.tvSizeCheckout.setText(cartItem.getSizeName());
        holder.binding.tvTtlPriceCheckout.setText(cartItem.getTotalPriceItem().toString());
        holder.binding.tvCount.setText(cartItem.getQuantity().toString());
    }

    @Override
    public int getItemCount() {
        if (cartItemsSelected != null) {
            return cartItemsSelected.size();
        }
        return 0;
    }

    public static class CheckOutViewHolder extends RecyclerView.ViewHolder {
        private final ItemProductCheckoutBinding binding;

        public CheckOutViewHolder(ItemProductCheckoutBinding itemProductCheckoutBinding) {
            super(itemProductCheckoutBinding.getRoot());
            binding = itemProductCheckoutBinding;
        }
    }
}
