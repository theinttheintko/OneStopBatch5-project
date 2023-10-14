package com.example.apisecurity.service;

import lombok.Getter;

public class Login {
    @Getter
    private final Jwt refreshToken;
    @Getter
    private final Jwt accessToken;
    private static final long ACCESS_VALIDITY = 2L;
    private static final long REFRESH_VALIDITY = 2880L;

    public Login(Jwt refreshToken, Jwt accessToken) {
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }

    public static Login of(long userId, String accessSecret, String refreshSecret) {
        return new Login(
                Jwt.of(userId, REFRESH_VALIDITY, refreshSecret),
                Jwt.of(userId, ACCESS_VALIDITY, accessSecret)
        );
    }

}
