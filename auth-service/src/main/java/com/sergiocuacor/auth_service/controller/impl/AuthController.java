package com.sergiocuacor.auth_service.controller.impl;


import com.sergiocuacor.auth_service.commons.constants.ApiPathConstants;
import com.sergiocuacor.auth_service.commons.dtos.LoginRequest;
import com.sergiocuacor.auth_service.commons.dtos.TokenResponse;
import com.sergiocuacor.auth_service.commons.dtos.UserRequest;
import com.sergiocuacor.auth_service.controller.AuthApi;
import com.sergiocuacor.auth_service.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.AUTH_ROUTE)
public class AuthController implements AuthApi {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<TokenResponse> createUser(UserRequest userRequest) {
        return ResponseEntity.ok(authService.createUser(userRequest));
    }

    @Override
    public ResponseEntity<TokenResponse> login(LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
