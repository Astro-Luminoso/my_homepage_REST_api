package me.h_yang.my_homepage.controller;


import me.h_yang.my_homepage.dto.CategoryDTO;
import me.h_yang.my_homepage.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/open/categories")
public class CategoryController {

    @Autowired
    public CategoryService categoryService;

    /**
     * Get all categories
     *
     * @return list of categories
     */
    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
