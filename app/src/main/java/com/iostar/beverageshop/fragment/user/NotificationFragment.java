package com.iostar.beverageshop.fragment.user;

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

import com.iostar.beverageshop.adapter.user.NotificationAdapter;
import com.iostar.beverageshop.databinding.FragmentNotificationBinding;
import com.iostar.beverageshop.model.Notification;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.INotificationService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationFragment extends Fragment {

    private FragmentNotificationBinding binding;
    private NotificationAdapter notificationAdapter;
    private List<Notification> notificationList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNotificationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        notificationList = new ArrayList<>();
        binding.rvNotification.setHasFixedSize(true);
        binding.rvNotification.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        getAllNotifications();

    }

    private void getAllNotifications() {
        BaseAPIService.createService(INotificationService.class).getNotificationByStatus(1).enqueue(new Callback<List<Notification>>() {
            @Override
            public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {
                notificationList = response.body();
                if (notificationList != null && notificationList.size() != 0) {
                    notificationAdapter = new NotificationAdapter(notificationList, getActivity());
                    binding.rvNotification.setAdapter(notificationAdapter);
                } else {
                    binding.imgEmpty.setVisibility(View.VISIBLE);
                    binding.tvTitle.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Notification>> call, Throwable t) {
                Log.e("err_get_noti", t.getMessage());
            }
        });
    }
}