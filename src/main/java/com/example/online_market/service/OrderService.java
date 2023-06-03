package com.example.online_market.service;

import com.example.online_market.entity.Orders;
import com.example.online_market.entity.User;

import java.util.List;

public interface OrderService {

    void addOrder(User user);

    void save(Orders order);

    List<Orders> findAll();
}
