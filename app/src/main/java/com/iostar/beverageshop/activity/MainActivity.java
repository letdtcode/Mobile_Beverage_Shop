package com.iostar.beverageshop.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationBarView;
import com.iostar.beverageshop.R;
import com.iostar.beverageshop.adapter.ViewPagerAdapter;
import com.iostar.beverageshop.databinding.ActivityMainBinding;
import com.iostar.beverageshop.fragment.AccountFragment;
import com.iostar.beverageshop.fragment.FavoriteFragment;
import com.iostar.beverageshop.fragment.HomeFragment;
import com.iostar.beverageshop.fragment.MenuFragment;
import com.iostar.beverageshop.fragment.SeachFragment;
import com.iostar.beverageshop.model.User;
import com.iostar.beverageshop.storage.DataLocalManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private final ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        String imgAvatarCallBack = intent.getStringExtra("data_result");
                        Glide.with(MainActivity.this)
                                .load(imgAvatarCallBack)
                                .into(binding.imgInfo);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialView();
        setEvent();
    }

    private void setEvent() {
        binding.cardViewImgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = DataLocalManager.getUser();
                Intent intent = new Intent(MainActivity.this, PersonalActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user", user);
                intent.putExtras(bundle);
//                startActivity(intent);
                mActivityResultLauncher.launch(intent);
            }
        });
        binding.imgGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }
        });
    }

    private void initialView() {
        setSupportActionBar(binding.toolBar);
        fragments.add(new HomeFragment());
        fragments.add(new FavoriteFragment());
        fragments.add(new MenuFragment());
        fragments.add(new SeachFragment());
        fragments.add(new AccountFragment());
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, fragments);
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
                        break;
                    case R.id.navigation_favorite:
                        binding.pagerMain.setCurrentItem(1);
                        break;
                    case R.id.navigation_menu:
                        binding.pagerMain.setCurrentItem(2);
                        break;
                    case R.id.navigation_seach:
                        binding.pagerMain.setCurrentItem(3);
                        break;
                    case R.id.navigation_account:
                        binding.pagerMain.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });
        String avatarUrl = DataLocalManager.getUser().getAvatar();
        if (avatarUrl==null || avatarUrl=="")
        {
            Glide.with(MainActivity.this).load(R.drawable.avatar_default).into(binding.imgInfo);
        }else {
            Glide.with(MainActivity.this).load(DataLocalManager.getUser().getAvatar()).into(binding.imgInfo);
        }
    }

}