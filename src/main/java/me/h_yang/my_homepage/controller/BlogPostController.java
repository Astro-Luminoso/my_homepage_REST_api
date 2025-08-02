package me.h_yang.my_homepage.controller;


import me.h_yang.my_homepage.dto.BriefBlogPostDTO;
import me.h_yang.my_homepage.service.BlogPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/open/blogposts")
public class BlogPostController {

    Logger logger = LoggerFactory.getLogger(BlogPostController.class);

    BlogPostService blogPostService;

    @Autowired
    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }


    @GetMapping
    public List<BriefBlogPostDTO> getAllBriefBlogPostsDetail(@RequestParam(required = false) String query,
                                                             @RequestParam(required = false) Long categoryId,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "8") int size) {

        logger.info("GET: /open/blogposts/ retrieving all blog post details briefly");

        return blogPostService.getBlogPostBriefByPage(query, categoryId, page, size);
    }
}
