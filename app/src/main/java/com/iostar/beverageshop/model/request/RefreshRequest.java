package com.iostar.beverageshop.model.request;

import com.google.gson.Gson;

public class RefreshRequest {
    private String tokenRefresh;

    public String getTokenRefresh() {
        return tokenRefresh;
    }

    public void setTokenRefresh(String tokenRefresh) {
        this.tokenRefresh = tokenRefresh;
    }
}
