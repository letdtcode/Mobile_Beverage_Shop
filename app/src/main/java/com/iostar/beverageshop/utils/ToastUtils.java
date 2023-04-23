package com.iostar.beverageshop.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.iostar.beverageshop.R;

public class ToastUtils {
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, android.widget.Toast.LENGTH_SHORT).show();
    }

    public static void showToastCustom(Context context, String message) {
        Toast toast = new Toast(context);
        View view = new View(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.layout_custom_toast, (ViewGroup) view.findViewById(R.id.layout_custom_toast));
        TextView tvToast = view.findViewById(R.id.message_toast_info);
        tvToast.setText(message);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}
