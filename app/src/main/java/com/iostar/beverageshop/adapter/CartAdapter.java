package com.iostar.beverageshop.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iostar.beverageshop.databinding.ItemProductInCartBinding;
import com.iostar.beverageshop.inteface.IOnCartItemCheckedListener;
import com.iostar.beverageshop.inteface.IOnToppingCheckedListener;
import com.iostar.beverageshop.model.CartItem;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.IProductService;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartItemViewHolder> {

    private final List<CartItem> cartItemList;
    private Context context;
    private IOnCartItemCheckedListener onCartItemCheckedListener;
    private String pathImg;

    public CartAdapter(List<CartItem> cartItemList, Context context, IOnCartItemCheckedListener onCartItemCheckedListener) {
        this.cartItemList = cartItemList;
        this.context = context;
        this.onCartItemCheckedListener = onCartItemCheckedListener;
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductInCartBinding binding = ItemProductInCartBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        context = parent.getContext();
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
        String productName = cartItem.getProductName();
        BaseAPIService.createService(IProductService.class).getImgPathProductByProductName(productName).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String pathImg = null;
                try {
                    pathImg = response.body().string();
                    getPathImageToOutside(pathImg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Glide.with(context).load(pathImg).into(holder.binding.imgCoffee);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        holder.binding.tvTitleProduct.setText(productName);
        holder.binding.tvToppings.setText(toppingList);
        holder.binding.tvSize.setText(cartItem.getSizeName());
        holder.binding.tvCount.setText(String.valueOf(cartItem.getQuantity()));
        Log.e("tag::", String.valueOf(cartItem.getTotalPriceItem().intValue()));
        holder.binding.tvTtlPrice.setText(String.valueOf(cartItem.getTotalPriceItem().intValue()));

        holder.binding.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.binding.checkBox.isChecked()) {
                    holder.binding.checkBox.setChecked(false);
                    onCartItemCheckedListener.onUnchecked(cartItem, pathImg);
                } else {
                    holder.binding.checkBox.setChecked(true);
                    onCartItemCheckedListener.onChecked(cartItem, pathImg);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (cartItemList != null) {
            return cartItemList.size();
        }
        return 0;
    }

    private void getPathImageToOutside(String pathImg) {
        this.pathImg = pathImg;
    }


    public static class CartItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemProductInCartBinding binding;

        public CartItemViewHolder(ItemProductInCartBinding itemProductInCartBinding) {
            super(itemProductInCartBinding.getRoot());
            binding = itemProductInCartBinding;
        }
    }
}
