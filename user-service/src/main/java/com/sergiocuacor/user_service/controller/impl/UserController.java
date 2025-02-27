package com.sergiocuacor.user_service.controller.impl;

import com.sergiocuacor.user_service.commons.constants.ApiPathConstants;
import com.sergiocuacor.user_service.commons.dtos.UserResponse;
import com.sergiocuacor.user_service.commons.dtos.UserUpdateRequest;
import com.sergiocuacor.user_service.commons.entities.UserModel;
import com.sergiocuacor.user_service.controller.UserApi;
import com.sergiocuacor.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.USER_ROUTE)
public class UserController implements UserApi {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserResponse> getUser(Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @Override
    public ResponseEntity<Void> updateUser(UserUpdateRequest user, Long userId) {

        userService.updateUser(user, userId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
