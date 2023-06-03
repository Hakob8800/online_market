package com.example.online_market.service;

import com.example.online_market.entity.User;

import java.util.Optional;

public interface UserService {
    void saveUser(User user);
    Optional<User> findByEmail(String email);

    User findById(int userId);

}
