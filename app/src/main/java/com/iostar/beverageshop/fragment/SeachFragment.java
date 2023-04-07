package com.iostar.beverageshop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.iostar.beverageshop.databinding.FragmentSeachBinding;

public class SeachFragment extends Fragment {
    private FragmentSeachBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSeachBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}