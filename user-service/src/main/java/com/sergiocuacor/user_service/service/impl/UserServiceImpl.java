package com.sergiocuacor.user_service.service.impl;


import com.sergiocuacor.user_service.commons.entities.UserModel;
import com.sergiocuacor.user_service.repository.UserRepository;
import com.sergiocuacor.user_service.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserModel getUser(Long userId) {
        return Optional.of(userId)
                .flatMap(userRepository::findById)
                .orElseThrow(()-> new RuntimeException("User with ID "+ userId+ " not found."));
    }

    @Override
    public void updateUser(UserModel userModel, Long userId) {
         Optional.of(userId)
                .flatMap(userRepository::findById)
                .map(existingUser -> updateUserFields(userModel, existingUser))
                .orElseThrow(() -> new RuntimeException("An error occurred while trying to update the user."));
    }

    private UserModel updateUserFields(UserModel userModel, UserModel existingUser) {

        if(userModel.getName() != null && !userModel.getName().isEmpty()){
            existingUser.setName(userModel.getName());
        }


            return existingUser;
    }

    @Override
    public void deleteUser(Long userId) {

    }
}
