package me.h_yang.my_homepage.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(nullable = false)
    private String categoryTitle;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<SubCategory> subCategoryList;

    // JPA empty constructor
    protected Category() {}


    public Category(String categoryTitle) {

        this.categoryTitle = categoryTitle;
    }

    public Long getId() {
        return id;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }


    public void setCategoryTitle(String categoryTitle) {

        this.categoryTitle = categoryTitle;
    }


    public List<SubCategory> getSubCategoryList() {

        return subCategoryList;
    }

}
