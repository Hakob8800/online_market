package com.example.online_market.contraller;

import com.example.online_market.entity.Category;
import com.example.online_market.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoriesController {

    private final CategoryRepo categoryRepo;

    @GetMapping
    public String categoriesPage(ModelMap modelMap){
        modelMap.addAttribute("categories",categoryRepo.findAll());
        return "categories";
    }
    @PostMapping("/add")
    public String addCategories(@ModelAttribute Category category){
        categoryRepo.save(category);
        return "redirect:/categories";
    }
    @GetMapping("/remove")
    public String removeCategory(@RequestParam("id") int id){
        categoryRepo.deleteById(id);
        return "redirect:/categories";
    }
}
