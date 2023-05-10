package com.iostar.beverageshop.adapter.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iostar.beverageshop.databinding.ItemProductHomeBinding;
import com.iostar.beverageshop.inteface.user.IClickItemProductListener;
import com.iostar.beverageshop.model.Product;

import java.util.List;

public class ProductHomeAdapter extends RecyclerView.Adapter<ProductHomeAdapter.ProductHomeViewHolder> {

    private final List<Product> productList;
    private Context mContext;
    private IClickItemProductListener iClickItemProductListener;

    public ProductHomeAdapter(List<Product> productList, Context context, IClickItemProductListener listener) {
        this.productList = productList;
        this.mContext = context;
        this.iClickItemProductListener = listener;
    }

    @NonNull
    @Override
    public ProductHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductHomeBinding binding = ItemProductHomeBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ProductHomeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHomeViewHolder holder, int position) {
        final Product product = productList.get(position);
        if (product == null) {
            return;
        }
        holder.binding.tvProductName.setText(product.getProductName());
        Glide.with(mContext).load(product.getPathImage()).into(holder.binding.imgProduct);
        holder.binding.cardViewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemProductListener.onClickItemProduct(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (productList != null) {
            return productList.size();
        }
        return 0;
    }

    public static class ProductHomeViewHolder extends RecyclerView.ViewHolder {
        private final ItemProductHomeBinding binding;

        public ProductHomeViewHolder(ItemProductHomeBinding itemProductHomeBinding) {
            super(itemProductHomeBinding.getRoot());
            binding = itemProductHomeBinding;
        }
    }

    public void release() {
        mContext = null;
    }
}
