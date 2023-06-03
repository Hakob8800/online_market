package com.example.online_market.service;

import com.example.online_market.entity.Category;

import java.util.List;

public interface CategoryService {
    void save(Category category);

    List<Category> findAll();
}
