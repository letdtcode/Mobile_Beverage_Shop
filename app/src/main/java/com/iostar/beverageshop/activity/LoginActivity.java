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
import com.iostar.beverageshop.model.User;
import com.iostar.beverageshop.model.request.LoginRequest;
import com.iostar.beverageshop.model.response.AuthResponse;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.IAuthService;
import com.iostar.beverageshop.service.IUserService;
import com.iostar.beverageshop.storage.DataLocalManager;
import com.iostar.beverageshop.utils.ToastUtils;

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
//                saveInfoUser("admin@gmail.com");
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
        String email = binding.edtEmail.getText().toString().trim();
        String password = binding.edtPassword.getText().toString().trim();

        LoginRequest loginRequest = new LoginRequest(email, password);
        JsonObject jsonReq = new Gson().toJsonTree(loginRequest).getAsJsonObject();
        BaseAPIService.createService(IAuthService.class).login(jsonReq).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.body() == null) {
                    ToastUtils.showToast(LoginActivity.this, "Email or password is incorrect");
                    return;
                }
                if (!response.body().getAccessToken().equals("")) {
                    AuthResponse authResponse = response.body();

                    DataLocalManager.saveAuthToken(authResponse);
                    Log.d("token", authResponse.toString());
//                    Call API get UserInfo
                    saveInfoUser(authResponse.getUserId());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    ToastUtils.showToast(LoginActivity.this, "Đăng nhập thành công");
                } else {
                    ToastUtils.showToast(LoginActivity.this, "Email or password is incorrect");
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                ToastUtils.showToast(LoginActivity.this, "Email or password is incorrect");
            }
        });
    }



    private void saveInfoUser(Long id) {
        BaseAPIService.createService(IUserService.class).getInfoUserById(id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user != null)
                    DataLocalManager.saveUser(user);
                Log.e("img",DataLocalManager.getUser().getId().toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                ToastUtils.showToast(LoginActivity.this,t.getMessage());
                Log.d("error",t.getMessage());
            }
        });
    }

}