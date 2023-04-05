package com.iostar.beverageshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.iostar.beverageshop.databinding.ActivityHelloBinding;

public class HelloActivity extends AppCompatActivity {
    private ActivityHelloBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHelloBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgLogo.setX(2000);
        binding.imgLogo.animate().translationXBy(-2000).setDuration(3000);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.layoutLoading.setVisibility(View.GONE);
                Intent intent = new Intent(HelloActivity.this, ConnectActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}