package com.iostar.beverageshop.adapter.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iostar.beverageshop.databinding.ItemFavoriteBinding;
import com.iostar.beverageshop.model.WishItem;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteItemViewHolder> {

    private List<WishItem> wishItemList;
    private Context context;
    private OnImgHeartClickListener listener;

    //    Interface
    public interface OnImgHeartClickListener {
        void onItemClick(int position);
    }

    //    Method
    public void setOnItemClickListener(OnImgHeartClickListener clickListener) {
        listener = clickListener;
    }

    public FavoriteAdapter(List<WishItem> wishItemList, Context context) {
        this.wishItemList = wishItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public FavoriteItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFavoriteBinding binding = ItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        context = parent.getContext();
        return new FavoriteAdapter.FavoriteItemViewHolder(binding,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteItemViewHolder holder, int position) {
        WishItem wishItem = wishItemList.get(position);
        holder.binding.tvCategory.setText(wishItem.getCategoryName());
        holder.binding.tvTitle.setText(wishItem.getProductName());
        holder.binding.tvPrice.setText(String.valueOf(wishItem.getPriceProduct()));
        holder.binding.tvRating.setText(String.valueOf(wishItem.getRating()));
        Glide.with(context).load(wishItem.getPathImg()).into(holder.binding.imgProductFavorite);
    }

    @Override
    public int getItemCount() {
        if (wishItemList != null) {
            return wishItemList.size();
        }
        return 0;
    }

    public static class FavoriteItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemFavoriteBinding binding;

        public FavoriteItemViewHolder(ItemFavoriteBinding itemFavoriteBinding, OnImgHeartClickListener listener) {
            super(itemFavoriteBinding.getRoot());
            binding = itemFavoriteBinding;

            binding.imgFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
