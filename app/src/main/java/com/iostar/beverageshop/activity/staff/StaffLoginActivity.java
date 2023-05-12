package com.iostar.beverageshop.activity.staff;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.iostar.beverageshop.databinding.ActivityStaffLoginBinding;
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

public class StaffLoginActivity extends AppCompatActivity {
    private ActivityStaffLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStaffLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setEvent();
    }

    private void setEvent() {
        binding.imgLogin.setOnClickListener(v -> login());
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
                    ToastUtils.showToast(StaffLoginActivity.this, "Email or password is incorrect");
                    return;
                }
                Boolean checkValid = false;
                AuthResponse authResponse = response.body();
                for (String role : authResponse.getRoles()) {
                    if (role.equals("ROLE_Admin")) {
                        checkValid = true;
                        break;
                    }
                }
                if (!response.body().getAccessToken().equals("") && checkValid == true) {
                    DataLocalManager.saveAuthToken(authResponse);
                    Log.d("token", authResponse.toString());
//                    Call API get UserInfo
                    saveInfoUser(authResponse.getUserId());
                    startActivity(new Intent(StaffLoginActivity.this, StaffActivity.class));
                    ToastUtils.showToast(StaffLoginActivity.this, "Đăng nhập thành công");
                } else {
                    ToastUtils.showToast(StaffLoginActivity.this, "Email or password is incorrect");
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.e("login_staff", t.getMessage());
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
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("save_info_user", t.getMessage());
            }
        });
    }
}