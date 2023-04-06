package com.iostar.beverageshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.iostar.beverageshop.R;
import com.iostar.beverageshop.databinding.ActivityLoginBinding;
import com.iostar.beverageshop.model.request.LoginRequest;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        login();
    }

    private void login() {
        String email = binding.edtEmail.getText().toString();
        String password = binding.edtPassword.getText().toString();

        LoginRequest loginRequest = new LoginRequest(email, password);
    }

}