package com.example.online_market.service.serviceImpl;

import com.example.online_market.entity.Products;
import com.example.online_market.repository.ProductRepo;
import com.example.online_market.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Value("${products.image.upload.folder}")
    private String uploadedFolderPath;

    private final ProductRepo productRepo;
    @Override
    public void save(Products products) {
        productRepo.save(products);
    }

    @Override
    public List<Products> findAll() {
        return productRepo.findAll();
    }

    @Override
    public void savePicture(Products products, MultipartFile multipartFile) throws IOException {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String fileName = System.nanoTime() + "_" + multipartFile.getOriginalFilename();
            File file = new File(uploadedFolderPath + fileName);
            multipartFile.transferTo(file);
            products.setImgPath(fileName);
        }
    }
    @Override
    public void deleteById(int id){
        productRepo.deleteById(id);
    }

    @Override
    public byte[] getImg(String imgPath) throws IOException {
        File file = new File(uploadedFolderPath + imgPath);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            return IOUtils.toByteArray(fis);
        }
        return null;
    }

}
