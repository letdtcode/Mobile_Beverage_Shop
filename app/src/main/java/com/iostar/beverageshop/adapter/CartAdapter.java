package com.iostar.beverageshop.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iostar.beverageshop.databinding.ItemProductInCartBinding;
import com.iostar.beverageshop.model.CartItem;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartItemViewHolder> {

    private final List<CartItem> cartItemList;

    public CartAdapter(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductInCartBinding binding = ItemProductInCartBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new CartAdapter.CartItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        CartItem cartItem = cartItemList.get(position);
        if (cartItem == null) {
            return;
        }
        String toppingList = "";
        for (String topping : cartItem.getToppingName()) {
            toppingList = toppingList + topping + " ";
        }
        holder.binding.tvTitleProduct.setText(cartItem.getProductName());
        holder.binding.tvToppings.setText(toppingList);
        holder.binding.tvSize.setText(cartItem.getSizeName());
        holder.binding.tvCount.setText(String.valueOf(cartItem.getQuantity()));
        Log.e("tag::",String.valueOf(cartItem.getTotalPriceItem().intValue()));
        holder.binding.tvTtlPrice.setText(String.valueOf(cartItem.getTotalPriceItem().intValue()));
    }

    @Override
    public int getItemCount() {
        if (cartItemList != null) {
            return cartItemList.size();
        }
        return 0;
    }


    public static class CartItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemProductInCartBinding binding;

        public CartItemViewHolder(ItemProductInCartBinding itemProductInCartBinding) {
            super(itemProductInCartBinding.getRoot());
            binding = itemProductInCartBinding;
        }
    }
}
