package com.iostar.beverageshop.activity.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.iostar.beverageshop.R;
import com.iostar.beverageshop.databinding.ActivityMainBinding;
import com.iostar.beverageshop.fragment.user.FavoriteFragment;
import com.iostar.beverageshop.fragment.user.HomeFragment;
import com.iostar.beverageshop.fragment.user.OrderFragment;
import com.iostar.beverageshop.fragment.user.ProfileFragment;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialView();
    }


    private void initialView() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.fragment_container, new HomeFragment());
        transaction.commit();

        binding.bottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int i1, @NonNull AnimatedBottomBar.Tab newTab) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                switch (newTab.getId()) {
                    case R.id.tab_home:
                        transaction.replace(R.id.fragment_container, new HomeFragment());
                        break;

                    case R.id.tab_order:
                        transaction.replace(R.id.fragment_container, new OrderFragment());
                        break;

                    case R.id.tab_favorite:
                        transaction.replace(R.id.fragment_container, new FavoriteFragment());
                        break;

                    case R.id.tab_profile:
                        transaction.replace(R.id.fragment_container, new ProfileFragment());
                        break;
                }
                transaction.commit();
            }

            @Override
            public void onTabReselected(int i, @NonNull AnimatedBottomBar.Tab tab) {

            }
        });
    }
}