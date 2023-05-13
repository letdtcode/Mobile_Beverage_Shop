package com.iostar.beverageshop.adapter.user;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iostar.beverageshop.databinding.ItemProductShowAllBinding;
import com.iostar.beverageshop.model.Product;

import java.util.List;

public class ProductViewAllAdapter extends RecyclerView.Adapter<ProductViewAllAdapter.ProductViewAllViewHolder> {
    private List<Product> productList;
    private Context mContext;

    public ProductViewAllAdapter(List<Product> productList, Context mContext) {
        this.productList = productList;
        this.mContext = mContext;
    }

    public void setFilteredList(List<Product> filteredList) {
        this.productList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewAllViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductShowAllBinding binding = ItemProductShowAllBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ProductViewAllAdapter.ProductViewAllViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewAllViewHolder holder, int position) {
        Product product = productList.get(position);
        if (product == null) {
            return;
        }
        holder.binding.tvProductName.setText(product.getProductName());
        Glide.with(mContext).load(product.getPathImage()).into(holder.binding.imgProduct);
        holder.binding.tvPrice.setText(product.getPriceDefault().toString());
    }

    @Override
    public int getItemCount() {
        if (productList != null) {
            return productList.size();
        }
        return 0;
    }

    public static class ProductViewAllViewHolder extends RecyclerView.ViewHolder {
        private final ItemProductShowAllBinding binding;

        public ProductViewAllViewHolder(ItemProductShowAllBinding itemProductShowAllBinding) {
            super(itemProductShowAllBinding.getRoot());
            binding = itemProductShowAllBinding;
        }
    }

    public void release() {
        mContext = null;
    }
}
