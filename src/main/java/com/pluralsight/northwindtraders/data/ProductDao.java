package com.pluralsight.northwindtraders.data;

import com.pluralsight.northwindtraders.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getAll();

    Product getById(int productid);
    List<Product> getByName(String productName);
    List<Product> getByCategoryId(int categoryId);
    List<Product> getByPrice(double productPrice);

}