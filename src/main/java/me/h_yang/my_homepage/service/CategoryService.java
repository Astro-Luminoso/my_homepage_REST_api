package me.h_yang.my_homepage.service;

import me.h_yang.my_homepage.entity.Category;
import me.h_yang.my_homepage.entity.SubCategory;
import me.h_yang.my_homepage.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;


    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    /**
     * Get all exist categories
     *
     * @return list of Category titles
     */
    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }

    /**
     * Get all subcategories of a category
     *
     * @param categoryTitle the title of the category
     * @return list of subcategory titles
     */
    public List<String> getSubCategoriesName(String categoryTitle) {

        return categoryRepository.findByCategoryTitle(categoryTitle)
                .getSubCategoryList().stream().map(SubCategory::getSubCategoryTitle).toList();
    }
}
