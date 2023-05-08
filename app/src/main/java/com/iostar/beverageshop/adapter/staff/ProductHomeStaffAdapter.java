package com.iostar.beverageshop.adapter.staff;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iostar.beverageshop.databinding.ItemStaffProductBinding;
import com.iostar.beverageshop.model.Category;
import com.iostar.beverageshop.model.Product;

import java.util.List;

public class ProductHomeStaffAdapter extends RecyclerView.Adapter<ProductHomeStaffAdapter.ProductHomeStaffViewHolder> {
    private final List<Product> productList;
    private final List<Category> categoryList;
    private Context mContext;

    public ProductHomeStaffAdapter(List<Product> productList, List<Category> categoryList, Context mContext) {
        this.productList = productList;
        this.categoryList = categoryList;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ProductHomeStaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStaffProductBinding binding = ItemStaffProductBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ProductHomeStaffAdapter.ProductHomeStaffViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHomeStaffViewHolder holder, int position) {
        final Product product = productList.get(position);
        if (product == null) {
            return;
        }
        holder.binding.tvProduct.setText(product.getProductName());
        holder.binding.tvPrice.setText(product.getPriceDefault().toString());
        holder.binding.tvRating.setText(product.getRating().toString());
        Glide.with(mContext).load(product.getPathImage()).into(holder.binding.imgProduct);

        Long categoryId = product.getCategoryId();
        String categoryName = "";
        for (Category categoryItem : categoryList) {
            if (categoryId == categoryItem.getId()) {
                categoryName = categoryItem.getCategoryName();
                break;
            }
        }
        holder.binding.tvCategory.setText(categoryName);
//        holder.binding.cardViewProduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                iClickItemProductListener.onClickItemProduct(product);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if (productList != null) {
            return productList.size();
        }
        return 0;
    }

    public static class ProductHomeStaffViewHolder extends RecyclerView.ViewHolder {
        private final ItemStaffProductBinding binding;

        public ProductHomeStaffViewHolder(ItemStaffProductBinding itemStaffProductBinding) {
            super(itemStaffProductBinding.getRoot());
            binding = itemStaffProductBinding;
        }
    }
}
