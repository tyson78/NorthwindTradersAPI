package com.pluralsight.northwindtraders.controller;

import com.pluralsight.northwindtraders.data.ProductDao;
import com.pluralsight.northwindtraders.model.Product;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    /* FIELDS */

    private ProductDao productDao;

    /* CONSTRUCTORS */

    @Autowired // injecting ProductDao bean here through constructor injection
    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    /* METHODS */

    // Retrieves all products in database using Postman as client
    // REST method: GET
    // Postman URL: GET http://localhost:8080/products/
    @RequestMapping(path="/products", method = RequestMethod.GET)
    public List<Product> findAll(){
        return productDao.getAll();
    }

    // Searches for products by productID in database using Postman as client
    // REST method: GET
    // Postman URL: GET http://localhost:8080/products/3
    @RequestMapping(path="/products/{id}", method = RequestMethod.GET)
    public Product findById(@PathVariable(name="id") int id, HttpServletResponse response){
        Product p = productDao.getById(id);

        if (p == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return p;
    }

    // Searches for products by productName in database using Postman as client
    // REST method: GET
    // Postman URL: GET http://localhost:8080/products/name/geitost
    @RequestMapping(path="/products/name/{productName}", method = RequestMethod.GET)
    public List<Product> findByName(@PathVariable(name = "productName") String productName, HttpServletResponse response){

        List<Product> p = new ArrayList<>();
        p = productDao.getByName(productName);

        if (p == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return p;
    }

    // Searches for products by categoryID in database using Postman as client
    // REST method: GET
    // Postman URL: GET http://localhost:8080/products/category/2
    @RequestMapping(path="/products/category/{id}", method = RequestMethod.GET)
    public List<Product> findByCategoryID(@PathVariable(name="id") int id, HttpServletResponse response){
        List<Product> p = productDao.getByCategoryId(id);

        if (p == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return p;
    }

    // Searches for products by price in database using Postman as client
    // REST method: GET
    // Postman URL: GET http://localhost:8080/price/2.99
    @RequestMapping(path="/products/price/{price}", method = RequestMethod.GET)
    public List<Product> findByPrice(@PathVariable(name="price") int price, HttpServletResponse response){
        List<Product> p = productDao.getByPrice(price);

        if (p == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return p;
    }

    // Inserts a record in database using Postman as client
    // REST method: POST
    // Postman URL: POST localhost:8080/products/
    // Postman Body (RAW & JSON): { // insert body info}
    @RequestMapping(path="/products", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Product insert(@RequestBody Product product){

        System.out.println("Incoming product: " + product);

        Product p = productDao.insert(product);
        System.out.println("Returned product: " + p);

        return p;
    }

    // Updates a record in database using Postman as client
    // REST method: PUT
    // Postman URL: PUT localhost:8080/products/102
    // Postman Body (RAW & JSON): { // insert body info}
    @RequestMapping(path="/products/{id}",method=RequestMethod.PUT)
    public void updateProduct (@PathVariable int id, @RequestBody Product product) {

        productDao.update(id, product);
    }

    // Deletes a record in database using Postman as client
    // REST method: DELETE
    // Postman URL: DELETE localhost:8080/products/102
    // Postman Body (RAW & JSON): { // insert body info}
    @RequestMapping(path="/products/{id}",method=RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProduct (@PathVariable int id) {

        productDao.delete(id);
    }


}
