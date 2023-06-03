package com.example.online_market.repository;

import com.example.online_market.entity.Cart;
import com.example.online_market.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart,Integer> {

    Optional<Cart> findByUserId(int userId);

}
