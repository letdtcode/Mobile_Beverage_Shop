package com.iostar.beverageshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iostar.beverageshop.databinding.ItemToppingProductBinding;
import com.iostar.beverageshop.inteface.IOnToppingCheckedListener;
import com.iostar.beverageshop.model.Topping;

import java.util.ArrayList;
import java.util.List;

public class ToppingDetailAdapter extends RecyclerView.Adapter<ToppingDetailAdapter.ToppingViewHolder> {

    private Context context;
    private final List<Topping> toppingList;

    private IOnToppingCheckedListener onToppingCheckedListener;

    public ToppingDetailAdapter(Context context, List<Topping> toppingList, IOnToppingCheckedListener onToppingCheckedListener) {
        this.context = context;
        this.toppingList = toppingList;
        this.onToppingCheckedListener=onToppingCheckedListener;
    }

    @NonNull
    @Override
    public ToppingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemToppingProductBinding binding = ItemToppingProductBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ToppingDetailAdapter.ToppingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ToppingViewHolder holder, int position) {
        final Topping topping = toppingList.get(position);
        if (topping == null) {
            return;
        }
        holder.binding.layoutClickItemTopping.setTag(position);

        holder.binding.txtToppingName.setText(topping.getToppingName());
        holder.binding.txtPriceTopping.setText(topping.getToppingPrice().toString());

        holder.binding.layoutClickItemTopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.binding.checkBoxTopping.isChecked()) {
                    holder.binding.checkBoxTopping.setChecked(false);
                    onToppingCheckedListener.onUnchecked(topping.getToppingName());
                } else {
                    holder.binding.checkBoxTopping.setChecked(true);
                    onToppingCheckedListener.onChecked(topping.getToppingName());
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (toppingList != null) {
            return toppingList.size();
        }
        return 0;
    }

    public class ToppingViewHolder extends RecyclerView.ViewHolder {
        private final ItemToppingProductBinding binding;

        public ToppingViewHolder(ItemToppingProductBinding itemToppingProductBinding) {
            super(itemToppingProductBinding.getRoot());
            binding = itemToppingProductBinding;
        }
    }
}
