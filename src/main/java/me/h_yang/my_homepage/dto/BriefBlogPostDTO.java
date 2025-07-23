package me.h_yang.my_homepage.dto;

import java.time.LocalDate;

public record BriefBlogPostDTO(Long postId, String title, String categoryTitle, LocalDate updateDate) {}
