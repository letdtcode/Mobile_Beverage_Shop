package com.iostar.beverageshop.activity.staff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.iostar.beverageshop.fragment.staff.StaffShowAllNotificationFragment;
import com.iostar.beverageshop.adapter.ViewPagerAdapter;
import com.iostar.beverageshop.databinding.ActivityStaffNotifiManageBinding;
import com.iostar.beverageshop.fragment.staff.StaffAddNotificationFragment;

import java.util.ArrayList;

public class StaffNotifiManageActivity extends AppCompatActivity {
    private ActivityStaffNotifiManageBinding binding;

    private ArrayList<Fragment> fragmentNotification;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStaffNotifiManageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        initial();
    }

    private void initial() {
        fragmentNotification = new ArrayList<>();
        fragmentNotification.add(new StaffAddNotificationFragment());
        fragmentNotification.add(new StaffShowAllNotificationFragment());

        viewPagerAdapter = new ViewPagerAdapter(StaffNotifiManageActivity.this, fragmentNotification);
        binding.viewPagerTabLayout.setAdapter(viewPagerAdapter);
        new TabLayoutMediator(binding.tabLayout, binding.viewPagerTabLayout, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Thêm thông báo");
                        break;
                    case 1:
                        tab.setText("Danh sách thông báo");
                        break;
                }
            }
        }).attach();
    }
}