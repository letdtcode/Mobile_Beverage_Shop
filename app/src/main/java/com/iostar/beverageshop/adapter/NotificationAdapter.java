package com.iostar.beverageshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iostar.beverageshop.databinding.ItemNotificationBinding;
import com.iostar.beverageshop.model.Notification;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationItemViewHolder> {
    private List<Notification> notificationList;
    private Context context;

    public NotificationAdapter(List<Notification> notificationList, Context context) {
        this.notificationList = notificationList;
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNotificationBinding binding = ItemNotificationBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        context = parent.getContext();
        return new NotificationAdapter.NotificationItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationItemViewHolder holder, int position) {
        Notification notification = notificationList.get(position);
        holder.binding.tvTitle.setText(notification.getTitle());
        holder.binding.tvContent.setText(notification.getContent());
        Glide.with(context).load(notification.getPathImgDescription()).into(holder.binding.imgNotification);
    }

    @Override
    public int getItemCount() {
        if (notificationList != null) {
            return notificationList.size();
        }
        return 0;
    }


    public static class NotificationItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemNotificationBinding binding;

        public NotificationItemViewHolder(ItemNotificationBinding itemNotificationBinding) {
            super(itemNotificationBinding.getRoot());
            binding = itemNotificationBinding;
        }
    }
}
