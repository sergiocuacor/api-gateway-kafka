package com.sergiocuacor.auth_service.controller;

import com.sergiocuacor.auth_service.commons.constants.ApiPathConstants;
import com.sergiocuacor.auth_service.commons.dtos.LoginRequest;
import com.sergiocuacor.auth_service.commons.dtos.TokenResponse;
import com.sergiocuacor.auth_service.commons.dtos.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.AUTH_ROUTE)
public interface AuthApi {

    @PostMapping("/register")
    ResponseEntity<TokenResponse> createUser(
            @RequestBody @Valid UserRequest userRequest
    );

    @PostMapping("/login")
    ResponseEntity<TokenResponse> login(
            @RequestBody @Valid LoginRequest loginRequest
    );

}
