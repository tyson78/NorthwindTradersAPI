package com.pluralsight.northwindtraders.data;

import com.pluralsight.northwindtraders.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getAll();

    Product getById(int productID);
    List<Product> getByName(String productName);
    List<Product> getByCategoryId(int categoryId);
    List<Product> getByPrice(double productPrice);

    Product insert(Product product);
    void update(int productID, Product product);
    void delete(int productID);

}