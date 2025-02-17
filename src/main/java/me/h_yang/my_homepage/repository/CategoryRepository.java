package me.h_yang.my_homepage.repository;

import me.h_yang.my_homepage.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    /**
     * Find all categories
     *
     * @return all categories
     */
    List<Category> findAll();

    /**
     * Find category by category title
     *
     * @param categoryTitle Category title
     * @return Category object
     */
    Category findByCategoryTitle(String categoryTitle);


    Category findById(long id);
}
