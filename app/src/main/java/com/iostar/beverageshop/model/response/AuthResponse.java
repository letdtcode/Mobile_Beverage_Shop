package com.iostar.beverageshop.model.response;

import java.util.Set;

public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private Set<String> roles;
    private long userId;
}
