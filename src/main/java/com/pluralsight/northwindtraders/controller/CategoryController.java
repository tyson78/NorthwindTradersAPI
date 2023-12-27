package com.pluralsight.northwindtraders.controller;

import com.pluralsight.northwindtraders.data.CategoryDao;
import com.pluralsight.northwindtraders.data.ProductDao;
import com.pluralsight.northwindtraders.model.Category;
import com.pluralsight.northwindtraders.model.Product;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {
    /* FIELDS */

    private CategoryDao categoryDao;

    /* CONSTRUCTORS */

    @Autowired // injecting CategoryDao bean here in the constructor
    public CategoryController(CategoryDao categoryDao){
        this.categoryDao = categoryDao;
    }

    /* METHODS */

    // output: http://localhost:8080/categories
    @RequestMapping(path="/categories", method = RequestMethod.GET)
    public List<Category> findAll(){
        return categoryDao.getAll();
    }

    // output: http://localhost:8080/categories/8
    @RequestMapping(path="/categories/{id}", method = RequestMethod.GET)
    public Category findById(@PathVariable(name="id") int id, HttpServletResponse response){
        Category c = categoryDao.getById(id);

        if (c == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return c;
    }

    @RequestMapping(path="/categories/name/{categoryName}", method = RequestMethod.GET)
    public List<Category> findByCategoryName(@PathVariable(name = "categoryName") String categoryName, HttpServletResponse response) {

        List<Category> c = new ArrayList<>();
        c = categoryDao.getByCategoryName(categoryName);

        if (c == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return c;
    }

    // POST into the database using Postman
    @RequestMapping(path="/categories", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Category insert(@RequestBody Category category){

        System.out.println("Incoming category: " + category);

        // TODO Put product in database
        Category c = categoryDao.insertCategory(category);
        System.out.println("Returned category: " + c);

        return c;
    }

}
