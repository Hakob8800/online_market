package com.example.online_market.service;

import com.example.online_market.entity.Products;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    void save(Products products);
    List<Products> findAll();
    void savePicture(Products products, MultipartFile multipartFile) throws IOException;
    void deleteById(int id);
    byte[] getImg(String imgPath) throws IOException;
}
