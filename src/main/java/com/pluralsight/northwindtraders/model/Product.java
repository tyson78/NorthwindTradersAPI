package com.pluralsight.northwindtraders.model;

public class Product {

    /*
     *   FIELDS
     */

    private int productId;
    private String productName;
    private int categoryId;
    private double unitPrice;

    /*
     *   CONSTRUCTORS
     */

    public Product() {
        this(0, "DefaultProductName", 0, 0.00);
    }

    public Product(int productId, String name, int categoryId, double unitPrice) {
        this.productId = productId;
        this.productName = name;
        this.categoryId = categoryId;
        this.unitPrice = unitPrice;
    }

    /*
     *   METHODS
     */
    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("productId=").append(productId);
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", categoryId=").append(categoryId);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append('}');
        return sb.toString();
    }
}
