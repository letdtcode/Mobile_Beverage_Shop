package com.iostar.beverageshop.fragment.staff.notification;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.iostar.beverageshop.adapter.staff.NotificationStaffAdapter;
import com.iostar.beverageshop.adapter.user.ProductHomeAdapter;
import com.iostar.beverageshop.databinding.FragmentStaffShowAllNotificationBinding;
import com.iostar.beverageshop.model.Notification;
import com.iostar.beverageshop.model.request.LoginRequest;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.INotificationService;
import com.iostar.beverageshop.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StaffShowAllNotificationFragment extends Fragment {
    private FragmentStaffShowAllNotificationBinding binding;
    private List<Notification> notificationList;
    private NotificationStaffAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStaffShowAllNotificationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initial();
        setEvent();
    }

    private void setEvent() {
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonObject jsonNotificationList = new Gson().toJsonTree(notificationList).getAsJsonObject();
                BaseAPIService.createService(INotificationService.class).updateStatus(jsonNotificationList).enqueue(new Callback<List<Notification>>() {
                    @Override
                    public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            ToastUtils.showToastCustom(getActivity(), "Cập nhật thành công");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Notification>> call, Throwable t) {
                        Log.e("err_update_stt_notilist", t.getMessage());
                    }
                });
            }
        });
    }

    private void initial() {
        binding.rvNotificationStaff.setHasFixedSize(true);
        binding.rvNotificationStaff.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        BaseAPIService.createService(INotificationService.class).getAllNotifications().enqueue(new Callback<List<Notification>>() {
            @Override
            public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {
                notificationList = response.body();
                if (notificationList != null && notificationList.size() > 0) {
                    adapter = new NotificationStaffAdapter(notificationList, getActivity());
                    adapter.setOnItemSelectedListener(new NotificationStaffAdapter.OnSpinnerSelectedListener() {
                        @Override
                        public void onItemSpinnerSelected(int position, int statusNotification) {
                            Notification item = notificationList.get(position);
                            item.setStatus(statusNotification);
                        }
                    });
                    binding.rvNotificationStaff.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Notification>> call, Throwable t) {
                Log.e("err_getall_notification", t.getMessage());
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.release();
        }
    }
}