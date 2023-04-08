package com.iostar.beverageshop.utils;

import android.content.Context;
import android.widget.Toast;

public class Utilities {
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, android.widget.Toast.LENGTH_SHORT).show();
    }
}
