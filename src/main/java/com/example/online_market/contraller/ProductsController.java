package com.example.online_market.contraller;

import com.example.online_market.entity.Products;
import com.example.online_market.service.CategoryService;
import com.example.online_market.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping
    public String productsPage(ModelMap modelMap){
        modelMap.addAttribute("products",productService.findAll());
        modelMap.addAttribute("categories",categoryService.findAll());
        return "products";
    }
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Products products,
                             @RequestParam("image") MultipartFile multipartFile) throws IOException {
        productService.savePicture(products,multipartFile);
        productService.save(products);
        return "redirect:/products";
    }
    @GetMapping("/remove")
    public String removeProduct(@RequestParam("id") int id){
        productService.deleteById(id);
        return "redirect:/products";
    }
}
