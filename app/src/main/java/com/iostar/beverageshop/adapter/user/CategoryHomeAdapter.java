package com.iostar.beverageshop.adapter.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iostar.beverageshop.databinding.ItemCategoryHomeBinding;
import com.iostar.beverageshop.model.Category;

import java.util.List;

public class CategoryHomeAdapter extends RecyclerView.Adapter<CategoryHomeAdapter.CategoryHomeViewHolder> {

    private final List<Category> categoryList;
    private Context mContext;
    private OnItemCategoryClickListener listener;

    //    Interface
    public interface OnItemCategoryClickListener {
        void onItemClick(int position);
    }

    //    Method
    public void setOnItemClickListener(OnItemCategoryClickListener clickListener) {
        listener = clickListener;
    }

    public CategoryHomeAdapter(List<Category> categoryList, Context mContext) {
        this.categoryList = categoryList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CategoryHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryHomeBinding binding = ItemCategoryHomeBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new CategoryHomeViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHomeViewHolder holder, int position) {
        Category category = categoryList.get(position);
        if (category == null) {
            return;
        }
        holder.binding.tvCategory.setText(category.getCategoryName());
        Glide.with(mContext).load(category.getPathImg()).into(holder.binding.imgCategory);
    }

    @Override
    public int getItemCount() {
        if (categoryList != null) {
            return categoryList.size();
        }
        return 0;
    }

    public static class CategoryHomeViewHolder extends RecyclerView.ViewHolder {
        private final ItemCategoryHomeBinding binding;

        public CategoryHomeViewHolder(ItemCategoryHomeBinding itemCategoryHomeBinding, OnItemCategoryClickListener listener) {
            super(itemCategoryHomeBinding.getRoot());
            binding = itemCategoryHomeBinding;

            binding.cardViewCategory.setOnClickListener(v -> listener.onItemClick(getAdapterPosition()));
        }
    }

    public void release() {
        mContext = null;
    }
}
