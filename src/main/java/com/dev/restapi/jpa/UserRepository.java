package com.dev.restapi.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restapi.user.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    
}
