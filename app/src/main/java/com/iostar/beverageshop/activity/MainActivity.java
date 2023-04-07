package com.iostar.beverageshop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.iostar.beverageshop.R;
import com.iostar.beverageshop.adapter.ViewPagerAdapter;
import com.iostar.beverageshop.databinding.ActivityMainBinding;
import com.iostar.beverageshop.fragment.AccountFragment;
import com.iostar.beverageshop.fragment.FavoriteFragment;
import com.iostar.beverageshop.fragment.HomeFragment;
import com.iostar.beverageshop.fragment.MenuFragment;
import com.iostar.beverageshop.fragment.SeachFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialView();
    }

    private void initialView() {
        fragments.add(new AccountFragment());
        fragments.add(new FavoriteFragment());
        fragments.add(new HomeFragment());
        fragments.add(new MenuFragment());
        fragments.add(new SeachFragment());
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this,fragments);
        binding.pagerMain.setAdapter(viewPagerAdapter);
        binding.pagerMain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        binding.navView.setSelectedItemId(R.id.navigation_home);
                        break;
                    case 1:
                        binding.navView.setSelectedItemId(R.id.navigation_favorite);
                        break;
                    case 2:
                        binding.navView.setSelectedItemId(R.id.navigation_menu);
                        break;
                    case 3:
                        binding.navView.setSelectedItemId(R.id.navigation_seach);
                        break;
                    case 4:
                        binding.navView.setSelectedItemId(R.id.navigation_account);
                        break;
                }
                super.onPageSelected(position);
            }
        });
        binding.navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        binding.pagerMain.setCurrentItem(0);
                    case R.id.navigation_favorite:
                        binding.pagerMain.setCurrentItem(1);
                    case R.id.navigation_menu:
                        binding.pagerMain.setCurrentItem(2);
                    case R.id.navigation_seach:
                        binding.pagerMain.setCurrentItem(3);
                    case R.id.navigation_account:
                        binding.pagerMain.setCurrentItem(4);
                }
                return true;
            }
        });
    }

}