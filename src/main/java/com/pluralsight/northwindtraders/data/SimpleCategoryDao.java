package com.pluralsight.northwindtraders.data;

import com.pluralsight.northwindtraders.model.Category;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Component
// @Primary
public class SimpleCategoryDao implements CategoryDao {

    /*
     *   STATIC VARIABLES
     */
    static List<Category> categoriesList;

    static {
        // initialize the categories list
        categoriesList = new ArrayList<>();
        categoriesList.add( new Category(1, "Games"));
        categoriesList.add( new Category(2, "Movies"));
        categoriesList.add( new Category(3, "Apps"));
    }

    /*
     *   CONSTRUCTORS
     */


    /*
     *   METHODS
     */

    @Override
    public Category getById(int id) {
        // Loop through list looking for a match
        for (Category c: categoriesList){
            if (c.getCategoryId() == id) return c;
        }
        return null;
    }

    @Override
    public List<Category> getAll() {
        return categoriesList;
    }

    @Override
    public List<Category> getByCategoryName(String categoryName) {
        return null;
    }

}
