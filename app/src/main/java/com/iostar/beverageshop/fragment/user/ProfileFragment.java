package com.iostar.beverageshop.fragment.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.iostar.beverageshop.ChangePasswordActivity;
import com.iostar.beverageshop.R;
import com.iostar.beverageshop.activity.LoginActivity;
import com.iostar.beverageshop.activity.user.ContactActivity;
import com.iostar.beverageshop.activity.user.OrderActivity;
import com.iostar.beverageshop.activity.user.PersonalActivity;
import com.iostar.beverageshop.databinding.FragmentProfileBinding;
import com.iostar.beverageshop.model.User;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.IUserService;
import com.iostar.beverageshop.storage.DataLocalManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initial();
        setEvent();
    }

    @Override
    public void onResume() {
        super.onResume();
        initial();
    }

    private void setEvent() {
        binding.itemLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataLocalManager.deleteInfo();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        binding.tvSeeInfoPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = DataLocalManager.getUser();
                Intent intent = new Intent(getActivity(), PersonalActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user", user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        binding.itemContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ContactActivity.class));
            }
        });
        binding.itemBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), OrderActivity.class));
            }
        });
        binding.itemChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
            }
        });
    }

    private void initial() {
        Long userId = DataLocalManager.getUser().getId();
        BaseAPIService.createService(IUserService.class).getInfoUserById(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user != null)
                    DataLocalManager.saveUser(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("error", t.getMessage());
            }
        });
        User user = DataLocalManager.getUser();
        if (user.getAvatar() == null || user.getAvatar().equals("")) {
            Glide.with(getActivity()).load(R.drawable.avatar_default).into(binding.imgAvatar);
        } else {
            Glide.with(getActivity()).load(user.getAvatar()).into(binding.imgAvatar);
        }
        binding.tvNameUser.setText(user.getUserName());
    }
}