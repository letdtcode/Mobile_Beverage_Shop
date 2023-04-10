package com.iostar.beverageshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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

        Bundle bundle=getIntent().getExtras();
        if (bundle==null) {
            return;
        }
        Product product= (Product) bundle.get("object_product");
        binding.tvNameProduct.setText(product.getProductName());
    }
}