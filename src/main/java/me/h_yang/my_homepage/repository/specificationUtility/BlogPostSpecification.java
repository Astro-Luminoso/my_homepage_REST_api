package me.h_yang.my_homepage.repository.specificationUtility;

import me.h_yang.my_homepage.entity.BlogPost;
import org.springframework.data.jpa.domain.Specification;

public class BlogPostSpecification {

    public static Specification<BlogPost> hasTitle(String title) {

        return title == null ?
                null : (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }

    public static Specification<BlogPost> hasCategoryId(Long categoryId) {
        return categoryId == null ?
                null : (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), categoryId);
    }
}
