package com.example.online_market.security;

import com.example.online_market.entity.User;
import com.example.online_market.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userByEmail = userService.findByEmail(username);
        if (userByEmail.isEmpty()) {
            throw new UsernameNotFoundException("User does not exist");
        }
        return new CurrentUser(userByEmail.get());
    }
}
