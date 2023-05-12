package com.iostar.beverageshop.activity.staff;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.iostar.beverageshop.databinding.ActivityDetailProductStaffBinding;
import com.iostar.beverageshop.model.Product;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.IProductService;
import com.iostar.beverageshop.utils.ToastUtils;
import com.iostar.beverageshop.utils.constants.PRODUCT_STATUS;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProductStaffActivity extends AppCompatActivity {
    private ActivityDetailProductStaffBinding binding;
    private List<String> statusProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailProductStaffBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intital();
        setEvent();
    }

    private void setEvent() {
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = binding.tvNameProduct.getText().toString();
                Integer status = null;
                switch (binding.spinnerStatusProduct.getSelectedItemPosition()) {
                    case 0:
                        status = 0;
                        break;
                    case 1:
                        status = 1;
                        break;
                }
                BaseAPIService.createService(IProductService.class).changeStatusOfProduct(productName, status).enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        if (response.code() == 200 && response != null) {
                            ToastUtils.showToastCustom(DetailProductStaffActivity.this, "Cập nhật trạng thái sản phẩm thành công");
                        }
                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {
                        Log.e("err", t.getMessage());
                    }
                });
            }
        });
        binding.imgBack.setOnClickListener(v -> finish());
    }

    private void intital() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
//        Get object from bundle
        Product product = (Product) bundle.get("object_product");
        binding.tvNameProduct.setText(product.getProductName());
        binding.tvPrice.setText(product.getPriceDefault().toString() + " K");
        binding.tvRating.setText(product.getRating().toString());
        binding.tvDescriptionProduct.setText(product.getDescription());
        binding.tvCountSold.setText(product.getSoldCount().toString());
        Glide.with(DetailProductStaffActivity.this).load(product.getPathImage()).into(binding.imgProduct);

        statusProduct = new ArrayList<>();
        statusProduct.add(PRODUCT_STATUS.STOP_SELLING);
        statusProduct.add(PRODUCT_STATUS.SELLING);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(DetailProductStaffActivity.this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, statusProduct);
        binding.spinnerStatusProduct.setAdapter(spinnerAdapter);
    }
}