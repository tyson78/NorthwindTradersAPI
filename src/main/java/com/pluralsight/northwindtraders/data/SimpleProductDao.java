package com.pluralsight.northwindtraders.data;

import com.pluralsight.northwindtraders.model.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Primary
public class SimpleProductDao implements ProductDao {

    static List<Product> productList;

    static {
        productList = new ArrayList<>();
        productList.add(new Product(1, "Pokemon Red", 1, 29.99));
        productList.add(new Product(2, "Pokemon Green", 1, 29.99));
        productList.add(new Product(3, "Pokemon Blue", 1, 29.99));
    }

    /*
     *   METHODS
     */

    @Override
    public List<Product> getAll() {
        return productList;
    }

    @Override
    public Product getById(int id) {
        Product p = null;
        // Use Streams interfaces to find a match
        Optional<Product> op = productList.stream().filter(product -> product.getProductId() == id).findFirst();

        if (op.isPresent()) p = op.get();

        return p;
    }


}
