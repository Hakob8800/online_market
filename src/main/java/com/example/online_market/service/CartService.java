package com.example.online_market.service;

import com.example.online_market.entity.Cart;
import com.example.online_market.entity.Products;
import com.example.online_market.entity.User;

import java.util.List;
import java.util.Optional;

public interface CartService {

    void createCart(User user);

    Optional<Cart> findByUserId(int userId);

    void addProduct(Products products, User user);

    double sumOfCart(int userId);

    List<Products> getProductsByUserId(int userId);

    void deleteProductById(int productId, int userId);

    void delete(Cart cart);
}
