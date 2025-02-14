package me.h_yang.my_homepage.controller.authentication;


import me.h_yang.my_homepage.entity.Category;
import me.h_yang.my_homepage.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    public CategoryService categoryService;

    /**
     * Get all categories including subcategories
     *
     * @return list of categories with subcategories
     */
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
