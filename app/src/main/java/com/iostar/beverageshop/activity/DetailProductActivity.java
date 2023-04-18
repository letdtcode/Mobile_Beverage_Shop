package com.iostar.beverageshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iostar.beverageshop.adapter.SizeDetailAdapter;
import com.iostar.beverageshop.adapter.ToppingDetailAdapter;
import com.iostar.beverageshop.databinding.ActivityDetailProductBinding;
import com.iostar.beverageshop.model.Product;
import com.iostar.beverageshop.model.Size;
import com.iostar.beverageshop.model.Topping;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.ISizeService;
import com.iostar.beverageshop.utils.ToastUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProductActivity extends AppCompatActivity {
    private ActivityDetailProductBinding binding;
    private SizeDetailAdapter sizeDetailAdapter;
    private ToppingDetailAdapter toppingDetailAdapter;
    private List<Size> sizeList;
    private List<Topping> toppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getInfoDetailProduct();
//        getInfoSizeOfProduct();
//        getInfoTopping();
        setEvent();
    }

//    private void getInfoTopping() {
//
//    }

    private void setEvent() {
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailProductActivity.this, MainActivity.class));
            }
        });
    }

    private void getInfoDetailProduct() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
//        Get object from bundle
        Product product = (Product) bundle.get("object_product");
        sizeList = (List<Size>) bundle.get("size_list");
        toppingList = (List<Topping>) bundle.get("topping_list");

//        Show Product
        binding.tvNameProduct.setText(product.getProductName());
        binding.tvPrice.setText(product.getPriceDefault().toString() + "K");
        binding.tvDescription.setText(product.getDescription());
        Glide.with(DetailProductActivity.this).load(product.getPathImage()).into(binding.imgProduct);

//        Show Size Of Product
        binding.rcvSizeProduct.setHasFixedSize(true);
        binding.rcvSizeProduct.setLayoutManager(new LinearLayoutManager(DetailProductActivity.this, RecyclerView.VERTICAL, false));

        sizeDetailAdapter = new SizeDetailAdapter(DetailProductActivity.this, sizeList, 1);
        binding.rcvSizeProduct.setAdapter(sizeDetailAdapter);

//        Show Topping Of Product
        binding.rcvToppingProduct.setHasFixedSize(true);
        binding.rcvToppingProduct.setLayoutManager(new LinearLayoutManager(DetailProductActivity.this, RecyclerView.VERTICAL, false));

        toppingDetailAdapter = new ToppingDetailAdapter(DetailProductActivity.this, toppingList);
        binding.rcvToppingProduct.setAdapter(toppingDetailAdapter);
    }

}