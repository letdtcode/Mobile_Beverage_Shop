package com.iostar.beverageshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.iostar.beverageshop.R;
import com.iostar.beverageshop.databinding.ActivityConnectBinding;

public class ConnectActivity extends AppCompatActivity {
    private ActivityConnectBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConnectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setEvent();
    }

    private void setEvent() {
        binding.btnLogin.setOnClickListener(v -> {
            Intent intent =new Intent(ConnectActivity.this,LoginActivity.class);
            startActivity(intent);
        });
        binding.btnRegister.setOnClickListener(v -> {
            Intent intent =new Intent(ConnectActivity.this,RegisterActivity.class);
            startActivity(intent);
        });
    }
}