package com.pluralsight.northwindtraders.controller;

import com.pluralsight.northwindtraders.data.ProductDao;
import com.pluralsight.northwindtraders.model.Product;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    /*
     *   FIELDS
     */

    private ProductDao productDao;


    /*
     *   CONSTRUCTORS
     */

    @Autowired
    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    /*
     *   METHODS
     */

    @RequestMapping(path="/products", method = RequestMethod.GET)
    public List<Product> findAll(){
        return productDao.getAll();
    }

    @RequestMapping(path="/products/{id}", method = RequestMethod.GET)
    public Product findById(@PathVariable(name="id") int id, HttpServletResponse response){
        Product p = productDao.getById(id);

        if (p == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return p;
    }
}
