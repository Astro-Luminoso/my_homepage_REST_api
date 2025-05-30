package me.h_yang.my_homepage.controller;


import me.h_yang.my_homepage.dto.CategoryDTO;
import me.h_yang.my_homepage.dto.SubCategoryDTO;
import me.h_yang.my_homepage.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/open/categories")
public class CategoryController {

    @Autowired
    public CategoryService categoryService;

    Logger logger = LoggerFactory.getLogger(CategoryController.class);

    /**
     * Get all categories
     *
     * @return list of categories
     */
    @GetMapping
    public List<CategoryDTO> getAllCategories() {

        logger.info("GET: /open/categories - get all categories");

        return categoryService.getAllCategories();
    }

    @GetMapping("/subcategories")
    public List<SubCategoryDTO> getAllSubCategoriesByCategoryId(@RequestParam("categoryId") long categoryId) {

        logger.info("GET: /open/categories/subcategories - get all subcategories for categoryId: {}", categoryId);

        return categoryService.getAllSubCategoriesByCategoryId(categoryId);
    }
}
