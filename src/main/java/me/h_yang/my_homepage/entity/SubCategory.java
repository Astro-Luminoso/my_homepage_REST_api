package me.h_yang.my_homepage.entity;


import jakarta.persistence.*;

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


    // JPA empty constructor
    protected SubCategory() {}


    /**
     * Constructor for a subcategory
     *
     * @param subCategoryTitle the title of the subcategory
     */
    public SubCategory(String subCategoryTitle) {

        this.subCategoryTitle = subCategoryTitle;
    }

    public String getSubCategoryTitle() {
        return subCategoryTitle;
    }

    public Category getCategory() {
        return category;
    }

    public void setSubCategoryTitle(String subCategoryTitle) {
        this.subCategoryTitle = subCategoryTitle;
    }



}
