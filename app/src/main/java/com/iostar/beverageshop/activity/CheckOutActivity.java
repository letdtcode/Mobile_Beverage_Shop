package com.iostar.beverageshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.iostar.beverageshop.R;
import com.iostar.beverageshop.databinding.ActivityCartBinding;

public class CheckOutActivity extends AppCompatActivity {
    private ActivityCartBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}