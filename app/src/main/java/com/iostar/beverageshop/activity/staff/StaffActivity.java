package com.iostar.beverageshop.activity.staff;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.iostar.beverageshop.R;
import com.iostar.beverageshop.databinding.ActivityStaffBinding;
import com.iostar.beverageshop.fragment.staff.StaffHomeFragment;
import com.iostar.beverageshop.fragment.staff.StaffOrderFragment;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class StaffActivity extends AppCompatActivity {
//    private ActivityStaffBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityStaffBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        initialView();
//    }
//
//    private void initialView() {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//
//        transaction.replace(binding.fragmentContainer.getId(), new StaffHomeFragment());
//        transaction.commit();
//
//        binding.bottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
//            @Override
//            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int i1, @NonNull AnimatedBottomBar.Tab newtab) {
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                switch (newtab.getId()) {
//                    case R.id.tab_home:
//                        transaction.replace(binding.fragmentContainer.getId(), new StaffHomeFragment());
//                        break;
//                    case R.id.tab_order:
//                        transaction.replace(binding.fragmentContainer.getId(), new StaffOrderFragment());
//                        break;
//                }
//                transaction.commit();
//            }
//
//            @Override
//            public void onTabReselected(int i, @NonNull AnimatedBottomBar.Tab tab) {
//
//            }
//        });
//    }
}