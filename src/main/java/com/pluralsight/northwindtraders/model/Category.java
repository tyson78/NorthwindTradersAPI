package com.pluralsight.northwindtraders.model;

public class Category {

    /*
     *   FIELDS
     */
    private int categoryId;
    private String categoryName;

    /*
     *   CONTRUCTORS
     */

    public Category() {
        this(0, "UnnamedCategory");
    }

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    /*
     *   METHODS
     */

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Category{");
        sb.append("categoryId=").append(categoryId);
        sb.append(", categoryName='").append(categoryName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
