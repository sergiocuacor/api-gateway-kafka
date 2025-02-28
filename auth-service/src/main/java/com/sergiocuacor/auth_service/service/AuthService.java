package com.sergiocuacor.auth_service.service;

import com.sergiocuacor.auth_service.commons.dtos.LoginRequest;
import com.sergiocuacor.auth_service.commons.dtos.TokenResponse;
import com.sergiocuacor.auth_service.commons.dtos.UserRequest;

public interface AuthService {
    TokenResponse createUser(UserRequest userRequest);
    TokenResponse login(LoginRequest loginRequest);
}
