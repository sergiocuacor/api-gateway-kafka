package com.sergiocuacor.user_service.repository;

import com.sergiocuacor.user_service.commons.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel,Long> {
}
