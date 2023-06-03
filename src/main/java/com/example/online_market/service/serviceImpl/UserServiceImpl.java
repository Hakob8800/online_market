package com.example.online_market.service.serviceImpl;

import com.example.online_market.entity.User;
import com.example.online_market.repository.UserRepo;
import com.example.online_market.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public void saveUser(User user) {
        userRepo.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User findById(int userId) {
        Optional<User> byId = userRepo.findById(userId);
        return byId.orElse(null);
    }
}
