package com.iostar.beverageshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.iostar.beverageshop.activity.staff.StaffLoginActivity;
import com.iostar.beverageshop.activity.user.MainActivity;
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
    private AuthResponse authResponse;
    private User userCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        initial();
        setEvent();
    }

    private void initial() {
//        DataLocalManager.deleteInfo();
    }

    private void setEvent() {
        binding.imgLogin.setOnClickListener(v -> login());
        binding.tvRegister.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
        binding.tvLoginStaff.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, StaffLoginActivity.class);
            startActivity(intent);
        });
    }

    private void login() {
        String email = binding.edtEmail.getText().toString().trim();
        String password = binding.edtPassword.getText().toString().trim();
        if (!checkValid(email, password)) {
            return;
        }
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
                    authResponse = response.body();
                    DataLocalManager.saveAuthToken(authResponse);
                    saveInfoUser(authResponse.getUserId());
//                    BaseAPIService.createService(IUserService.class).getInfoUserById(authResponse.getUserId()).enqueue(new Callback<User>() {
//                        @Override
//                        public void onResponse(Call<User> call, Response<User> response) {
//                            User user = response.body();
//                            if (response.isSuccessful() && user != null) {
//                                saveInfoUser(authResponse, user);
//                                ToastUtils.showToast(LoginActivity.this, "Đăng nhập thành công");
//                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<User> call, Throwable t) {
//                            Log.d("error", t.getMessage());
//                        }
//                    });
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

    private boolean checkValid(String email, String password) {
        if (email.isEmpty()) {
            binding.edtEmail.setError("Vui lòng nhập email");
            return false;
        }
        if (password.isEmpty()) {
            binding.edtPassword.setError("Vui lòng nhập password");
            return false;
        }
        return true;
    }

    private void saveInfoUser(Long userId) {
        BaseAPIService.createService(IUserService.class).getInfoUserById(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user != null && response.isSuccessful()) {
                    DataLocalManager.saveUser(user);
                    ToastUtils.showToast(LoginActivity.this, "Đăng nhập thành công");
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("error", t.getMessage());
            }
        });
    }

}