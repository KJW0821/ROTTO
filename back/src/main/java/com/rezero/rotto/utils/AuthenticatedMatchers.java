package com.rezero.rotto.utils;

public class AuthenticatedMatchers {

    private AuthenticatedMatchers() {}

    public static final String[] matcherArray = {
            "/",
            "/user/signup",
            "/auth/login",
            "/auth/refresh",
            "/api-docs",
            "/api-docs/json",
            "/api-docs/**",
            "/swagger-ui/index.html",
            "/swagger-ui.html",
            "/swagger-ui/**"

    };
}
