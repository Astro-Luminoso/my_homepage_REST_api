package me.h_yang.my_homepage.repository;

import me.h_yang.my_homepage.entity.BlogPost;
import me.h_yang.my_homepage.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlogPostRepository extends CrudRepository<BlogPost, Long> {

    List<BlogPost> findAllByOrderByUpdatedDateDesc();

    List<BlogPost> findAllByCategoryOrderByUpdatedDateDesc(Category category);
}
