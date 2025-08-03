package me.h_yang.my_homepage.dto;

import java.util.List;

public record BlogPostListDTO(List<BriefBlogPostDTO> postList, long totalCount) {}
