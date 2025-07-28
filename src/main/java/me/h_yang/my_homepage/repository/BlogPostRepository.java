package me.h_yang.my_homepage.repository;

import me.h_yang.my_homepage.entity.BlogPost;
import me.h_yang.my_homepage.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long>, JpaSpecificationExecutor<BlogPost> {

    List<BlogPost> findAllByOrderByUpdatedDateDesc();

    List<BlogPost> findAllByCategoryOrderByUpdatedDateDesc(Category category);

    Page<BlogPost> findAll(Specification<BlogPost> spec, Pageable pageable);
}
