package com.pluralsight.northwindtraders.controller;

import com.pluralsight.northwindtraders.data.CategoryDao;
import com.pluralsight.northwindtraders.data.ProductDao;
import com.pluralsight.northwindtraders.model.Category;
import com.pluralsight.northwindtraders.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class CategoryController {
    /*
     *   FIELDS
     */

    private CategoryDao categoryDao;


    /*
     *   CONSTRUCTORS
     */

    @Autowired
    public CategoryController(CategoryDao categoryDao){
        this.categoryDao = categoryDao;
    }

    /*
     *   METHODS
     */

    @RequestMapping(path="/categories", method = RequestMethod.GET)
    public List<Category> findAll(){
        return categoryDao.getAll();
    }

    @RequestMapping(path="/categories/{id}", method = RequestMethod.GET)
    public Category findById(@PathVariable(name="id") int id){
        return categoryDao.getById(id);
    }

}
