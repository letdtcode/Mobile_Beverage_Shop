package com.iostar.beverageshop;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.iostar.beverageshop.activity.user.CheckOutActivity;
import com.iostar.beverageshop.databinding.ActivityDetailProductStaffBinding;
import com.iostar.beverageshop.model.Product;
import com.iostar.beverageshop.utils.constants.PRODUCT_STATUS;

import java.util.ArrayList;
import java.util.List;

public class DetailProductStaffActivity extends AppCompatActivity {
    private ActivityDetailProductStaffBinding binding;
    private List<String> statusProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailProductStaffBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intital();
    }

    private void intital() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
//        Get object from bundle
        Product product = (Product) bundle.get("object_product");
        binding.tvNameProduct.setText(product.getProductName());
        binding.tvPrice.setText(product.getPriceDefault().toString());
        binding.tvRating.setText(product.getRating().toString());
        binding.tvDescriptionProduct.setText(product.getDescription());
        Glide.with(DetailProductStaffActivity.this).load(product.getPathImage()).into(binding.imgProduct);

        statusProduct = new ArrayList<>();
        statusProduct.add(PRODUCT_STATUS.STOP_SELLING);
        statusProduct.add(PRODUCT_STATUS.SELLING);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(DetailProductStaffActivity.this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, statusProduct);
        binding.spinnerStatusProduct.setAdapter(spinnerAdapter);

        switch (product.getStatus()) {
            case 0:
                binding.spinnerStatusProduct.setSelection(0);
                break;
            case 1:
                binding.spinnerStatusProduct.setSelection(1);
                break;
        }

    }
}