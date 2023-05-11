package com.iostar.beverageshop.fragment.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.iostar.beverageshop.ContactActivity;
import com.iostar.beverageshop.R;
import com.iostar.beverageshop.activity.LoginActivity;
import com.iostar.beverageshop.activity.user.PersonalActivity;
import com.iostar.beverageshop.databinding.FragmentProfileBinding;
import com.iostar.beverageshop.model.User;
import com.iostar.beverageshop.storage.DataLocalManager;

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
    }

    private void initial() {
        User user = DataLocalManager.getUser();
        if (user.getAvatar() != null && user.getAvatar() != "") {
            Glide.with(getActivity()).load(user.getAvatar()).into(binding.imgAvatar);
        } else {
            Glide.with(getActivity()).load(R.drawable.avatar_default).into(binding.imgAvatar);
        }
        binding.tvNameUser.setText(user.getUserName());
    }
}