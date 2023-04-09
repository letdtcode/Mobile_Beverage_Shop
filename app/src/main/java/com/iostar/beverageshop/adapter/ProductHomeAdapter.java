package com.iostar.beverageshop.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iostar.beverageshop.R;
import com.iostar.beverageshop.databinding.ItemCategoryHomeBinding;
import com.iostar.beverageshop.databinding.ItemProductHomeBinding;
import com.iostar.beverageshop.model.Category;
import com.iostar.beverageshop.model.Product;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.IProductService;
import com.iostar.beverageshop.utils.Utilities;

import java.io.InputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductHomeAdapter extends RecyclerView.Adapter<ProductHomeAdapter.ProductHomeViewHolder> {

    private final List<Product> productList;
    private Context mContext;

    public ProductHomeAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.mContext = context;
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
        Product product = productList.get(position);
        if (product == null) {
            return;
        }
        holder.binding.tvProductName.setText(product.getProductName());
        BaseAPIService.createService(IProductService.class).getImageProduct(product.getId()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body()!=null) {
                    InputStream inputStream = response.body().byteStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    if (bitmap != null) {
                        Glide.with(mContext).load(bitmap).into(holder.binding.imgProduct);
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Utilities.showToast(mContext,"show anh that bai");
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
}
