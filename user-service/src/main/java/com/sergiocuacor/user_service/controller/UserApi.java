package com.sergiocuacor.user_service.controller;

import com.sergiocuacor.user_service.commons.constants.ApiPathConstants;
import com.sergiocuacor.user_service.commons.entities.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.USER_ROUTE)
public interface UserApi {

    @GetMapping("/{userId}")
    public ResponseEntity<UserModel> getUser(@PathVariable Long userId);

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@RequestBody UserModel user, @PathVariable Long userId);

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId);
}