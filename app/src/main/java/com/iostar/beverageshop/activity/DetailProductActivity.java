package com.iostar.beverageshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.iostar.beverageshop.R;
import com.iostar.beverageshop.databinding.ActivityDetailProductBinding;
import com.iostar.beverageshop.databinding.ActivityHelloBinding;
import com.iostar.beverageshop.model.Product;

public class DetailProductActivity extends AppCompatActivity {
    private ActivityDetailProductBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getInfoDetailProduct();
        setEvent();
    }

    private void setEvent() {
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailProductActivity.this,MainActivity.class));
            }
        });
    }

    private void getInfoDetailProduct() {
        Bundle bundle=getIntent().getExtras();
        if (bundle==null) {
            return;
        }
        Product product= (Product) bundle.get("object_product");
        binding.tvNameProduct.setText(product.getProductName());
        binding.tvPrice.setText(product.getPriceDefault().toString()+"K");
        binding.tvDescription.setText(product.getDescription());
//        Glide.with(this).load(product.getImageProduct()).into(binding.imgProduct);
    }
}