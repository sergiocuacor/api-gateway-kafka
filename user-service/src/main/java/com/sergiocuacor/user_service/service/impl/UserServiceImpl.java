package com.sergiocuacor.user_service.service.impl;


import com.sergiocuacor.user_service.commons.dtos.UserResponse;
import com.sergiocuacor.user_service.commons.dtos.UserUpdateRequest;
import com.sergiocuacor.user_service.commons.entities.UserModel;
import com.sergiocuacor.user_service.commons.exceptions.user_exceptions.UserNotFoundException;
import com.sergiocuacor.user_service.repository.UserRepository;
import com.sergiocuacor.user_service.service.UserService;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse getUser(Long userId) {
        return Optional.of(userId)
                .flatMap(userRepository::findById)
                .map(this::mapToDto)
                .orElseThrow(()-> new UserNotFoundException(userId));
    }



    @Override
    public void updateUser(UserUpdateRequest updateRequest, Long userId) {
         Optional.of(userId)
                .flatMap(userRepository::findById)
                .map(existingUser -> updateUserFields(existingUser,updateRequest))
                 .map(userRepository::save)
                .orElseThrow(() -> new RuntimeException("An error occurred while trying to update the user."));
    }



    @Override
    public void deleteUser(Long userId) {
        Optional.of(userId)
                .flatMap(userRepository::findById)
                .ifPresent(userRepository::delete);
    }

    private UserResponse mapToDto(UserModel userModel) {

        return UserResponse.builder()
                .userId(userModel.getUserId())
                .email(userModel.getEmail())
                .username(userModel.getUsername())
                .role(userModel.getRole())
                .build();

    }

    private UserModel updateUserFields(UserModel existingUser, UserUpdateRequest updateRequest) {
        Optional.ofNullable(updateRequest.getUsername())
                .ifPresent(existingUser::setUsername);
        Optional.ofNullable(updateRequest.getEmail())
                .ifPresent(existingUser::setEmail);
        return existingUser;
    }
}
