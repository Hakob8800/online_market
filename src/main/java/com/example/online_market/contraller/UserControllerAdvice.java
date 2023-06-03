package com.example.online_market.contraller;

import com.example.online_market.entity.User;
import com.example.online_market.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class UserControllerAdvice {

    @ModelAttribute("user")
    public User curretntUser(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null){
            return currentUser.getUser();
        }
        return null;
    }
}
