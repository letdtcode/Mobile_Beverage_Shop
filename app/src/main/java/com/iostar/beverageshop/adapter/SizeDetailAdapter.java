package com.iostar.beverageshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iostar.beverageshop.databinding.ItemSizeProductBinding;
import com.iostar.beverageshop.inteface.IOnSizeClickListener;
import com.iostar.beverageshop.model.Size;

import java.util.List;

public class SizeDetailAdapter extends RecyclerView.Adapter<SizeDetailAdapter.SizeViewHolder> {
    private Context context;
    private final List<Size> sizeList;
    private int mCheckedPosition = 1;

    private IOnSizeClickListener onSizeClickListener;

    public SizeDetailAdapter(Context context, List<Size> sizeList, int checkedPosition, IOnSizeClickListener onSizeClickListener) {
        this.context = context;
        this.sizeList = sizeList;
        this.mCheckedPosition = checkedPosition;
        this.onSizeClickListener = onSizeClickListener;
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

        holder.binding.layoutClickItemSize.setTag(position);
        holder.binding.txtSizeName.setText(size.getSizeName());
        holder.binding.txtPricePlusSize.setText(size.getPricePlus().toString());
        holder.binding.radioSize.setChecked(position == mCheckedPosition);

        holder.binding.layoutClickItemSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheckedPosition = (int) v.getTag();
                notifyDataSetChanged();
                onSizeClickListener.onSizeClick(size.getSizeName());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (sizeList != null) {
            return sizeList.size();
        }
        return 0;
    }


    public class SizeViewHolder extends RecyclerView.ViewHolder {
        private final ItemSizeProductBinding binding;

        public SizeViewHolder(ItemSizeProductBinding itemSizeProductBinding) {
            super(itemSizeProductBinding.getRoot());
            binding = itemSizeProductBinding;
        }
    }
}
