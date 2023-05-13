package com.iostar.beverageshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.iostar.beverageshop.databinding.ActivityRegisterBinding;
import com.iostar.beverageshop.model.request.RegisterRequest;
import com.iostar.beverageshop.model.response.AuthResponse;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.IAuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setEvent();
    }

    private void setEvent() {
        binding.imgRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        binding.tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void register() {
        String firstName = binding.edtFirstName.getText().toString().trim();
        String lastName = binding.edtLastName.getText().toString().trim();
        String email = binding.edtEmail.getText().toString().trim();
        String phone = binding.edtPhone.getText().toString().trim();
        String address = binding.edtAddress.getText().toString().trim();
        String password = binding.edtPasswordRegister.getText().toString();

        if (checkValid()) {
            RegisterRequest registerRequest = new RegisterRequest(firstName, lastName, email, password, address, phone);
            JsonObject jsonReq = new Gson().toJsonTree(registerRequest).getAsJsonObject();
            Log.d("err", jsonReq.toString());
            BaseAPIService.createService(IAuthService.class).register(jsonReq).enqueue(new Callback<AuthResponse>() {
                @Override
                public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                    Toast.makeText(RegisterActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }

                @Override
                public void onFailure(Call<AuthResponse> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                    String json = jsonReq.toString();
                    Log.d("JSON", json);
                }
            });
        }


    }

    private boolean checkValid() {
        if (binding.edtFirstName.getText().toString().trim().isEmpty()) {
            binding.edtFirstName.setError("Please enter your First Name");
            return false;
        }
        if (binding.edtLastName.getText().toString().trim().isEmpty()) {
            binding.edtLastName.setError("Please enter your Last Name");
            return false;
        }
        if (binding.edtEmail.getText().toString().trim().isEmpty()) {
            binding.edtEmail.setError("Please enter your Email");
            return false;
        }
        if (binding.edtPhone.getText().toString().trim().isEmpty()) {
            binding.edtPhone.setError("Please enter your Phone Number");
            return false;
        }
        if (binding.edtAddress.getText().toString().trim().isEmpty()) {
            binding.edtAddress.setError("Please enter your Address");
            return false;
        }
        if (binding.edtPasswordRegister.getText().toString().trim().isEmpty()) {
            binding.edtPasswordRegister.setError("Please enter your Password");
            return false;
        }
        if (binding.edtConfirmPassword.getText().toString().trim().isEmpty()) {
            binding.edtConfirmPassword.setError("Please enter your Confirm Password");
            return false;
        }

        if (!binding.edtPasswordRegister.getText().toString().trim().equals(binding.edtConfirmPassword.getText().toString().trim())) {
            binding.edtConfirmPassword.setError("Password doesn't match !");
            return false;
        }
        return true;
    }
}