package com.iostar.beverageshop.fragment.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.iostar.beverageshop.R;
import com.iostar.beverageshop.databinding.FragmentAccountBinding;
import com.iostar.beverageshop.model.User;
import com.iostar.beverageshop.storage.DataLocalManager;


public class AccountFragment extends Fragment {
    private FragmentAccountBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        User user = DataLocalManager.getUser();
        if (user.getAvatar() != null && user.getAvatar() != "") {
            Glide.with(getActivity()).load(user.getAvatar()).into(binding.imgAvatar);
        } else {
            Glide.with(getActivity()).load(R.drawable.avatar_default).into(binding.imgAvatar);
        }
    }
}