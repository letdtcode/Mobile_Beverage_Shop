package com.iostar.beverageshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.iostar.beverageshop.databinding.ActivityLoginBinding;
import com.iostar.beverageshop.model.request.LoginRequest;
import com.iostar.beverageshop.model.response.AuthResponse;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.IAuthService;
import com.iostar.beverageshop.storage.DataLocalManager;
import com.iostar.beverageshop.utils.Utilities;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setEvent();
    }

    private void setEvent() {
        binding.imgLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        binding.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void login() {
        String email = binding.edtEmail.getText().toString();
        String password = binding.edtPassword.getText().toString();

        LoginRequest loginRequest = new LoginRequest(email, password);
        JsonObject jsonReq = new Gson().toJsonTree(loginRequest).getAsJsonObject();
        BaseAPIService.createService(IAuthService.class).login(jsonReq).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.body() == null) {
                    Utilities.showToast(LoginActivity.this, "Email or password is incorrect");
                    return;
                }
                if (!response.body().getAccessToken().equals("")) {
                    AuthResponse authResponse = response.body();

                    DataLocalManager.saveAuthToken(authResponse);
                    Log.d("token",authResponse.toString());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    Utilities.showToast(LoginActivity.this, "Đăng nhập thành công");
                } else {
                    Utilities.showToast(LoginActivity.this, "Email or password is incorrect");
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.d("error", t.getMessage());
            }
        });
    }

}