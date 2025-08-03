package me.h_yang.my_homepage.dto;

import java.util.List;

public record BlogPostList(List<BriefBlogPostDTO> postList, long totalCount) {}
