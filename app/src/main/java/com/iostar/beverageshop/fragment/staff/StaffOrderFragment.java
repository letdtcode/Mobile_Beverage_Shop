package com.iostar.beverageshop.fragment.staff;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.iostar.beverageshop.adapter.ViewPagerOrderAdapter;
import com.iostar.beverageshop.databinding.FragmentStaffOrderBinding;
import com.iostar.beverageshop.fragment.staff.order.OrderCanceledStaffFragment;
import com.iostar.beverageshop.fragment.staff.order.OrderSuccessStaffFragment;
import com.iostar.beverageshop.fragment.staff.order.OrderWaitingConfirmStaffFragment;
import com.iostar.beverageshop.fragment.staff.order.OrderWaitingDeliveryStaffFragment;

import java.util.ArrayList;

public class StaffOrderFragment extends Fragment {
    private FragmentStaffOrderBinding binding;
    private ArrayList<Fragment> fragmentOrderDetail;
    private ViewPagerOrderAdapter viewPagerOrderAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStaffOrderBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initial();
//        setEvent();
    }

    private void initial() {
        fragmentOrderDetail = new ArrayList<>();
        fragmentOrderDetail.add(new OrderWaitingConfirmStaffFragment());
        fragmentOrderDetail.add(new OrderWaitingDeliveryStaffFragment());
        fragmentOrderDetail.add(new OrderSuccessStaffFragment());
        fragmentOrderDetail.add(new OrderCanceledStaffFragment());

        viewPagerOrderAdapter = new ViewPagerOrderAdapter(getActivity(), fragmentOrderDetail);
        binding.viewPagerTabLayout.setAdapter(viewPagerOrderAdapter);
        new TabLayoutMediator(binding.tabLayout, binding.viewPagerTabLayout, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Chờ xác nhận");
                        break;
                    case 1:
                        tab.setText("Đang giao");
                        break;
                    case 2:
                        tab.setText("Đã nhận");
                        break;
                    case 3:
                        tab.setText("Đã hủy");
                        break;
                }
            }
        }).attach();
    }
}