package com.iostar.beverageshop.activity.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.iostar.beverageshop.databinding.ActivityListProductBinding;

public class ListProductActivity extends AppCompatActivity {
    private ActivityListProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}