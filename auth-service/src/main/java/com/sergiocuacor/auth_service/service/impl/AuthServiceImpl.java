package com.sergiocuacor.auth_service.service.impl;

import com.sergiocuacor.auth_service.commons.dtos.LoginRequest;
import com.sergiocuacor.auth_service.commons.dtos.TokenResponse;
import com.sergiocuacor.auth_service.commons.dtos.UserRequest;
import com.sergiocuacor.auth_service.commons.entities.UserModel;
import com.sergiocuacor.auth_service.commons.exceptions.auth_exceptions.EmailAlreadyExistsException;
import com.sergiocuacor.auth_service.repository.UserRepository;
import com.sergiocuacor.auth_service.service.AuthService;
import com.sergiocuacor.auth_service.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(
            UserRepository userRepository,
            JwtService jwtService,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ){
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public TokenResponse createUser(UserRequest userRequest) {

        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
        	 throw new EmailAlreadyExistsException(userRequest.getEmail());
        }


        return Optional.of(userRequest)
                .map(this::mapToEntity)
                .map(userRepository::save)
                .map(createdUser -> jwtService.generateToken(createdUser.getUserId()))
                .orElseThrow(()-> new RuntimeException("There was an error while creating the user"));
    }

    public TokenResponse login(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserModel userModel = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()-> new RuntimeException("User not found"));
        return jwtService.generateToken(userModel.getUserId());
    }

    private UserModel mapToEntity(UserRequest userRequest) {
        return UserModel.builder()
                .email(userRequest.getEmail())
                .username(userRequest.getName())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .role("USER")
                .build();
    }
}
