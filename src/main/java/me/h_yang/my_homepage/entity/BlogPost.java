package me.h_yang.my_homepage.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_post_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @JoinColumn(name="category_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @JoinColumn(name = "sub_category_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private SubCategory subCategory;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Date updatedDate;


    // JPA empty constructor
    protected BlogPost() {}

    public BlogPost(String title, Category category, SubCategory subCategory, String author, String content) {
        this.title = title;
        this.category = category;
        this.subCategory = subCategory;
        this.author = author;
        this.content = content;
        this.updatedDate = new Date();
    }
}
