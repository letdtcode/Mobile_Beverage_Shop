package com.iostar.beverageshop.adapter.staff;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iostar.beverageshop.databinding.ItemStaffNotificationBinding;
import com.iostar.beverageshop.model.Notification;
import com.iostar.beverageshop.utils.constants.NOTIFICATION_STATUS;

import java.util.ArrayList;
import java.util.List;

public class NotificationStaffAdapter extends RecyclerView.Adapter<NotificationStaffAdapter.NotificationStaffViewHolder> {
    private List<Notification> notificationList;
    private Context mContext;
    private List<String> nameStatusNotification;
    private static ArrayAdapter<String> spinnerAdapter;
    private NotificationStaffAdapter.OnSpinnerSelectedListener listener;

    //    Interface
    public interface OnSpinnerSelectedListener {
        void onItemSpinnerSelected(int position, int statusNotification);
    }

    //    Method
    public void setOnItemSelectedListener(NotificationStaffAdapter.OnSpinnerSelectedListener selectedListener) {
        listener = selectedListener;
    }

    public NotificationStaffAdapter(List<Notification> notificationList, Context mContext) {
        this.notificationList = notificationList;
        this.mContext = mContext;

        nameStatusNotification = new ArrayList<>();
        nameStatusNotification.add(NOTIFICATION_STATUS.DISABLE);
        nameStatusNotification.add(NOTIFICATION_STATUS.ENABLE);

        spinnerAdapter = new ArrayAdapter<String>(mContext, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, nameStatusNotification);
    }

    @NonNull
    @Override
    public NotificationStaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStaffNotificationBinding binding = ItemStaffNotificationBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
//        Integer abcd = 10;
//        Log.e("test", abcd.toString());
        return new NotificationStaffAdapter.NotificationStaffViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationStaffViewHolder holder, int position) {
        Notification notification = notificationList.get(position);
        Log.e("test", String.valueOf(notification.getContent()));
        if (notification == null) {
            return;
        }
        holder.binding.tvTitle.setText(notification.getTitle());
        holder.binding.tvContent.setText(notification.getContent());
        Glide.with(mContext).load(notification.getPathImgDescription()).into(holder.binding.imgNotification);
    }

    @Override
    public int getItemCount() {
        if (notificationList != null) {
            return notificationList.size();
        }
        return 0;
    }

    public static class NotificationStaffViewHolder extends RecyclerView.ViewHolder {
        private final ItemStaffNotificationBinding binding;

        public NotificationStaffViewHolder(ItemStaffNotificationBinding itemStaffNotificationBinding, NotificationStaffAdapter.OnSpinnerSelectedListener listener) {
            super(itemStaffNotificationBinding.getRoot());
            binding = itemStaffNotificationBinding;
//            Integer abc = 5;
            binding.spinnerStatusNoti.setAdapter(spinnerAdapter);
//            Log.e("test", abc.toString());
            binding.spinnerStatusNoti.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0:
                            listener.onItemSpinnerSelected(getBindingAdapterPosition(), 0);
                            break;
                        case 1:
                            listener.onItemSpinnerSelected(getBindingAdapterPosition(), 1);
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
    }

    public void release() {
        mContext = null;
        spinnerAdapter = null;
        listener = null;
    }
}
