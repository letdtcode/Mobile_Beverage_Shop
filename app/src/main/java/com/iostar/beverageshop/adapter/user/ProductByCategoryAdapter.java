package com.iostar.beverageshop.adapter.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iostar.beverageshop.databinding.ItemProductInCategoryBinding;
import com.iostar.beverageshop.model.Product;

import java.util.List;

public class ProductByCategoryAdapter extends RecyclerView.Adapter<ProductByCategoryAdapter.ProductByCategoryViewHolder> {
    private final List<Product> productList;
    private Context mContext;

    public ProductByCategoryAdapter(List<Product> productList, Context mContext) {
        this.productList = productList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ProductByCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductInCategoryBinding binding = ItemProductInCategoryBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ProductByCategoryAdapter.ProductByCategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductByCategoryViewHolder holder, int position) {
        Product product = productList.get(position);
        if (product == null) {
            return;
        }
        holder.binding.tvProductName.setText(product.getProductName());
        Glide.with(mContext).load(product.getPathImage()).into(holder.binding.imgProduct);
        holder.binding.tvPrice.setText(product.getPriceDefault().toString() + " K");
        holder.binding.tvRating.setText(product.getRating().toString());
    }

    @Override
    public int getItemCount() {
        if (productList != null) {
            return productList.size();
        }
        return 0;
    }

    public static class ProductByCategoryViewHolder extends RecyclerView.ViewHolder {
        private final ItemProductInCategoryBinding binding;

        public ProductByCategoryViewHolder(ItemProductInCategoryBinding itemProductInCategoryBinding) {
            super(itemProductInCategoryBinding.getRoot());
            binding = itemProductInCategoryBinding;
        }
    }

    public void release() {
        mContext = null;
    }
}
