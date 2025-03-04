package com.sergiocuacor.api_gateway.services;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.function.Predicate;

@Service
public class RouterValidator {

    public static final List<String> openEndpoints = List.of(
            "v1/auth/login",
            "v1/auth/register",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/webjars/**"
    );

    public Predicate<ServerHttpRequest> isSecured = serverHttpRequest -> openEndpoints.stream().noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));

}
