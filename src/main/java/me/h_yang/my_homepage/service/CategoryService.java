package me.h_yang.my_homepage.service;

import me.h_yang.my_homepage.dto.CategoryDTO;
import me.h_yang.my_homepage.dto.SubCategoryDTO;
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
     * @return list of categoryDTOs
     */
    public List<CategoryDTO> getAllCategories() {

        return categoryRepository.findAll().stream()
                .map(category -> new CategoryDTO(category.getId(), category.getCategoryTitle())).toList();
    }


    /**
     * get all subcategories of a category
     *
     * @param categoryId id of the category
     * @return list of subcategories
     */
    public List<SubCategoryDTO> getAllSubCategoriesByCategoryId(long categoryId) {

        return categoryRepository.findById(categoryId).getSubCategoryList().stream()
                .map(subCategory -> new SubCategoryDTO(subCategory.getId(), subCategory.getSubCategoryTitle()))
                .toList();
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
