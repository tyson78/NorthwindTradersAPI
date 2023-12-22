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
}
