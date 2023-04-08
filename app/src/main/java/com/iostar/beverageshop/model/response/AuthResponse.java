package com.iostar.beverageshop.model.response;

import java.util.Set;

public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private Set<String> roles;
    private long userId;

    public AuthResponse(String accessToken, String refreshToken, Set<String> roles, long userId) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.roles = roles;
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
