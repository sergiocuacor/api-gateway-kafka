package com.sergiocuacor.user_service.service;

import com.sergiocuacor.user_service.commons.entities.UserModel;
import com.sergiocuacor.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserModel getUser(Long userId);
    void updateUser(UserModel userModel, Long userId);
    void deleteUser(Long userId);

}
