package me.h_yang.my_homepage.repository;

import me.h_yang.my_homepage.entity.SubCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends CrudRepository<SubCategory, Long> {

}
