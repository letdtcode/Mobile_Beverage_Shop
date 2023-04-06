package com.iostar.beverageshop;

import android.app.Application;

import com.iostar.beverageshop.storage.DataLocalManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataLocalManager.init(getApplicationContext());
    }
}
