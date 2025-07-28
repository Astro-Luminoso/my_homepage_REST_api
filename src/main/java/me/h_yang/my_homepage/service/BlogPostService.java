package me.h_yang.my_homepage.service;

import me.h_yang.my_homepage.dto.BriefBlogPostDTO;
import me.h_yang.my_homepage.entity.BlogPost;
import me.h_yang.my_homepage.repository.BlogPostRepository;
import me.h_yang.my_homepage.repository.specificationUtility.BlogPostSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostService {


    BlogPostRepository blogPostRepository;

    @Autowired
    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }


    public List <BriefBlogPostDTO> getBlogPostBriefByPage (String title, Long categoryId, int page, int size) {

        Specification<BlogPost> spec = Specification
                .where(BlogPostSpecification.hasTitle(title))
                .and(BlogPostSpecification.hasCategoryId(categoryId));

        Pageable pageable = PageRequest.of(page, size, Sort.by("updatedDate").descending());

        return blogPostRepository.findAll(spec, pageable)
                .stream()
                .map(post -> new BriefBlogPostDTO(
                        post.getId(), post.getTitle(),
                        post.getCategory().getCategoryTitle(),
                        post.getUpdatedDate()))
                .toList();
    }

}
