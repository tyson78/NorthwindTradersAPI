package com.pluralsight.northwindtraders.data;

import com.pluralsight.northwindtraders.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

@Component
@Primary
public class JdbcCategoryDao implements CategoryDao {
    private DataSource dataSource;

    @Autowired
    public JdbcCategoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Category getById(int id) {
        Category result = null;

        String sql = "SELECT * FROM Categories WHERE CategoryID = ?;";

        try (Connection c = dataSource.getConnection()) {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                result = new Category(rs.getInt("CategoryID"), rs.getString("CategoryName"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Category> getAll() {
        List<Category> results = new ArrayList<>();

        String sql = """
                SELECT * FROM Categories;
                """;

        try (Connection c = dataSource.getConnection()) {
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                results.add(new Category(rs.getInt("CategoryID"), rs.getString("CategoryName")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    @Override
    public List<Category> getByCategoryName(String categoryName) {
        List<Category> results = new ArrayList<>();

        String sql = """
                SELECT * FROM Categories 
                WHERE CategoryName = ?;
                """;

        try (Connection c = dataSource.getConnection()) {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, categoryName);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                results.add(new Category(rs.getInt("CategoryID"), rs.getString("CategoryName")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }
}
