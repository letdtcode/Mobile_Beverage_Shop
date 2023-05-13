package com.iostar.beverageshop.activity.user;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.iostar.beverageshop.databinding.ActivityChangePasswordBinding;
import com.iostar.beverageshop.model.User;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.IUserService;
import com.iostar.beverageshop.storage.DataLocalManager;
import com.iostar.beverageshop.utils.ToastUtils;

import at.favre.lib.crypto.bcrypt.BCrypt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {
    private ActivityChangePasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setEvent();
    }

    private void setEvent() {
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwordCurrent = binding.edtPasswordCurrent.getText().toString().trim();
                String passwordNew = binding.edtPasswordNew.getText().toString().trim();
                String confirmPassword = binding.edtConfirmPassword.getText().toString().trim();
                if (checkValid(passwordCurrent, passwordNew, confirmPassword)) {
                    String mail = DataLocalManager.getUser().getMail();
                    BaseAPIService.createService(IUserService.class).changePassword(mail, passwordNew).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                ToastUtils.showToastCustom(ChangePasswordActivity.this, "Đã cập nhật lại mật khẩu");
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.e("err_changepasss", t.getMessage());
                        }
                    });
                }
            }
        });
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean checkValid(String oldPassword, String newPassword, String confirmPassword) {
        String bcryptHashString = DataLocalManager.getUser().getPassword();
        BCrypt.Result result = BCrypt.verifyer().verify(oldPassword.toCharArray(), bcryptHashString);
        if (!checkNonEmpty(oldPassword, newPassword, confirmPassword)) {
            return false;
        }
        if (!result.verified) {
            binding.edtPasswordCurrent.setError("Mật khẩu không chính xác");
            return false;
        }
        if (!newPassword.equals(confirmPassword)) {
            binding.edtConfirmPassword.setError("Mật khẩu không khớp");
            return false;
        }
        return true;
    }

    private boolean checkNonEmpty(String oldPassword, String newPassword, String confirmPassword) {
        if (oldPassword.isEmpty()) {
            binding.edtPasswordCurrent.setError("Vui lòng điền mật khẩu hiện tại");
            return false;
        }
        if (newPassword.isEmpty()) {
            binding.edtPasswordNew.setError("Vui lòng điền mật khẩu mới");
            return false;
        }
        if (confirmPassword.isEmpty()) {
            binding.edtConfirmPassword.setError("Vui lòng xác nhận mật khẩu");
            return false;
        }
        return true;
    }
}