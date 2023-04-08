package com.iostar.beverageshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iostar.beverageshop.databinding.ItemCategoryHomeBinding;
import com.iostar.beverageshop.model.Category;

import java.util.List;

public class CategoryHomeAdapter extends RecyclerView.Adapter<CategoryHomeAdapter.CategoryHomeViewHolder> {

    private final List<Category> categoryList;

    public CategoryHomeAdapter(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryHomeBinding binding = ItemCategoryHomeBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new CategoryHomeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHomeViewHolder holder, int position) {
        Category category = categoryList.get(position);
        if(category==null){
            return;
        }
        holder.binding.tvCategory.setText(category.getCategoryName());
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

        public CategoryHomeViewHolder(ItemCategoryHomeBinding itemCategoryHomeBinding) {
            super(itemCategoryHomeBinding.getRoot());
            binding = itemCategoryHomeBinding;
        }
    }
}
