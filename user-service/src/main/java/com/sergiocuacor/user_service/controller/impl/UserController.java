package com.sergiocuacor.user_service.controller.impl;

import com.sergiocuacor.user_service.commons.constants.ApiPathConstants;
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
    public ResponseEntity<UserModel> getUser(Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateUser(UserModel user, Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long userId) {
        return null;
    }
}
