package com.iostar.beverageshop.adapter.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iostar.beverageshop.databinding.ItemCategoryShowAllBinding;
import com.iostar.beverageshop.model.Category;

import java.util.List;

public class CategoyViewAllAdapter extends RecyclerView.Adapter<CategoyViewAllAdapter.CategoryViewAllViewHolder> {
    private final List<Category> categoryList;
    private Context mContext;

    private CategoryHomeAdapter.OnItemCategoryClickListener listener;

    //    Interface
    public interface OnItemCategoryClickListener {
        void onItemClick(int position);
    }

    //    Method
    public void setOnItemClickListener(CategoryHomeAdapter.OnItemCategoryClickListener clickListener) {
        listener = clickListener;
    }

    public CategoyViewAllAdapter(List<Category> categoryList, Context mContext) {
        this.categoryList = categoryList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CategoryViewAllViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryShowAllBinding binding = ItemCategoryShowAllBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new CategoyViewAllAdapter.CategoryViewAllViewHolder(binding,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewAllViewHolder holder, int position) {
        Category category = categoryList.get(position);
        if (category == null) {
            return;
        }
        holder.binding.tvCategoryName.setText(category.getCategoryName());
        Glide.with(mContext).load(category.getPathImg()).into(holder.binding.imgCategory);
    }

    @Override
    public int getItemCount() {
        if (categoryList != null) {
            return categoryList.size();
        }
        return 0;
    }

    public static class CategoryViewAllViewHolder extends RecyclerView.ViewHolder {
        private final ItemCategoryShowAllBinding binding;

        public CategoryViewAllViewHolder(ItemCategoryShowAllBinding itemCategoryShowAllBinding, CategoryHomeAdapter.OnItemCategoryClickListener listener) {
            super(itemCategoryShowAllBinding.getRoot());
            binding = itemCategoryShowAllBinding;

            binding.cardViewCategory.setOnClickListener(v -> listener.onItemClick(getBindingAdapterPosition()));
        }
    }

    public void release() {
        mContext = null;
    }

}
