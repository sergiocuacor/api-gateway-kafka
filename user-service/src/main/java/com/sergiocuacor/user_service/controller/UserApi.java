package com.sergiocuacor.user_service.controller;

import com.sergiocuacor.user_service.commons.constants.ApiPathConstants;
import com.sergiocuacor.user_service.commons.dtos.UserResponse;
import com.sergiocuacor.user_service.commons.dtos.UserUpdateRequest;
import com.sergiocuacor.user_service.commons.entities.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.USER_ROUTE)
public interface UserApi {

    @GetMapping("/user-info")
    ResponseEntity<UserResponse> getUser(@RequestHeader("X-User-Id") Long userId);


    @PutMapping("/{userId}")
    ResponseEntity<UserResponse> updateUser(
            @RequestHeader("X-User-Id") Long userId,
            @RequestBody UserUpdateRequest updateRequest
    );

    @DeleteMapping("/{userId}")
    ResponseEntity<Void> deleteUser(@RequestHeader("X-User-Id") Long userId);
}