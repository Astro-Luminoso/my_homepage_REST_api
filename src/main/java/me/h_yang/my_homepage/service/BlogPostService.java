package me.h_yang.my_homepage.service;

import me.h_yang.my_homepage.dto.BriefBlogPostDTO;
import me.h_yang.my_homepage.repository.BlogPostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostService {


    BlogPostRepository blogPostRepository;

    @Autowired
    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }


    public List<BriefBlogPostDTO> getAllBlogPostBriefs() {

        return blogPostRepository.findAllByOrderByUpdatedDateDesc()
                .stream()
                .map(post -> new BriefBlogPostDTO(
                        post.getId(), post.getTitle(),
                        post.getCategory().getCategoryTitle(),
                        post.getUpdatedDate()))
                .toList();

    }

}
