package com.iostar.beverageshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.iostar.beverageshop.databinding.ActivityContactBinding;

public class ContactActivity extends AppCompatActivity {
    private ActivityContactBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setEvent();
    }

    private void setEvent() {
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}