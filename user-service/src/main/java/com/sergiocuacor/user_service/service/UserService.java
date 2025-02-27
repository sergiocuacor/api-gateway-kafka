package com.sergiocuacor.user_service.service;

import com.sergiocuacor.user_service.commons.dtos.UserResponse;
import com.sergiocuacor.user_service.commons.dtos.UserUpdateRequest;
import com.sergiocuacor.user_service.commons.entities.UserModel;
import com.sergiocuacor.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserResponse getUser(Long userId);
    void updateUser(UserUpdateRequest userUpdateRequest, Long userId);
    void deleteUser(Long userId);

}
