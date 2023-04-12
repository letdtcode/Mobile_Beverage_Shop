package com.iostar.beverageshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.iostar.beverageshop.R;
import com.iostar.beverageshop.databinding.ActivityConnectBinding;
import com.iostar.beverageshop.databinding.ActivityPersonalBinding;
import com.iostar.beverageshop.model.Product;
import com.iostar.beverageshop.model.User;

public class PersonalActivity extends AppCompatActivity {
    private ActivityPersonalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getInfoUser();
        setEvent();
    }

    private void setEvent() {

    }

    private void getInfoUser() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        User user = (User) bundle.get("object_user");
        Glide.with(PersonalActivity.this).load(user.getAvatar()).into(binding.imgProfile);
        binding.edUsername.setText(user.getUserName());
        binding.edtFirstName.setText(user.getFirstName());
        binding.edtLastName.setText(user.getLastName());
        binding.edtEmail.setText(user.getMail());
        binding.edtNumberPhone.setText(user.getPhone());
        binding.edtAddress.setText(user.getAddress());
    }

}