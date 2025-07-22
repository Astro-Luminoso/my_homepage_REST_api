package me.h_yang.my_homepage.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_category_id")
    private Long id;

    @Column()
    private String subCategoryTitle;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "subCategory", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<BlogPost> blogPosts;


    // JPA empty constructor
    protected SubCategory() {}


    /**
     * Constructor for a subcategory
     *
     * @param subCategoryTitle the title of the subcategory
     */
    public SubCategory(Category category, String subCategoryTitle) {

        this.category = category;
        this.subCategoryTitle = subCategoryTitle;
    }

    public String getSubCategoryTitle() {
        return subCategoryTitle;
    }

    /**
     * get the id of the subcategory
     *
     * @return the id of the subcategory
     */
    public Long getId() {
        return id;
    }
    public void setSubCategoryTitle(String subCategoryTitle) {
        this.subCategoryTitle = subCategoryTitle;
    }

    public String toString() {
        return String.format("SubCategory id: %d, categoryId: %d,title: %s", id, category.getId(), subCategoryTitle);
    }



}
