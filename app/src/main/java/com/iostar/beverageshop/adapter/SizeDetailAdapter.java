package com.iostar.beverageshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iostar.beverageshop.databinding.ItemProductHomeBinding;
import com.iostar.beverageshop.databinding.ItemSizeProductBinding;
import com.iostar.beverageshop.model.Product;
import com.iostar.beverageshop.model.Size;

import java.util.List;

public class SizeDetailAdapter extends RecyclerView.Adapter<SizeDetailAdapter.SizeViewHolder> {
    private Context context;
    private final List<Size> sizeList;
    private long mCheckedPosition;

    public SizeDetailAdapter(Context context, List<Size> sizeList, long checkedPosition) {
        this.context = context;
        this.sizeList = sizeList;
        this.mCheckedPosition = mCheckedPosition;
    }

    @NonNull
    @Override
    public SizeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSizeProductBinding binding = ItemSizeProductBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new SizeDetailAdapter.SizeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SizeViewHolder holder, int position) {
        final Size size = sizeList.get(position);
        if (size == null) {
            return;
        }
        holder.binding.txtSizeName.setText(size.getSizeName());
        holder.binding.txtPricePlusSize.setText(size.getPricePlus().toString());
//        holder.binding.radioSize.setChecked(size.getSizeName());
//        Glide.with(mContext).load(product.getPathImage()).into(holder.binding.imgProduct);
//        holder.binding.cardViewProduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                iClickItemProductListener.onClickItemProduct(product);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if (sizeList != null) {
            return sizeList.size();
        }
        return 0;
    }


    public static class SizeViewHolder extends RecyclerView.ViewHolder {
        private final ItemSizeProductBinding binding;

        public SizeViewHolder(ItemSizeProductBinding itemSizeProductBinding) {
            super(itemSizeProductBinding.getRoot());
            binding = itemSizeProductBinding;
        }
    }
}
