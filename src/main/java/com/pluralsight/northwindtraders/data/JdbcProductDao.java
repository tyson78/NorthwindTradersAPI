package com.pluralsight.northwindtraders.data;

import com.pluralsight.northwindtraders.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class JdbcProductDao implements ProductDao {

    private DataSource dataSource;

    @Autowired
    public JdbcProductDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> getAll() {
        // TODO submit this sql to the database
        String sql = "SELECT * FROM products;";

        List<Product> results = new ArrayList<>();

        try (Connection c = dataSource.getConnection()){
            PreparedStatement s = c.prepareStatement(sql);
            ResultSet rs = s.executeQuery();

            while(rs.next()){
                results.add( new Product(rs.getInt("ProductId"), rs.getString("ProductName"), rs.getInt("CategoryId"), rs.getDouble("UnitPrice")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return results;
    }

    @Override
    public Product getById(int productid) {
        // TODO submit this sql to the database
        String sql = "SELECT * FROM products WHERE ProductId = ?;";

        Product result = null;

        try (Connection c = dataSource.getConnection()){
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, productid);
            ResultSet rs = s.executeQuery();

            while(rs.next()){
                result = new Product(rs.getInt("ProductId"), rs.getString("ProductName"), rs.getInt("CategoryId"), rs.getDouble("UnitPrice"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public List<Product> getByName(String productName) {

        List<Product> results = new ArrayList<>();

        String sql = "SELECT * FROM products WHERE ProductName = ?;";

        try (Connection c = dataSource.getConnection()){
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, productName);
            ResultSet rs = s.executeQuery();

            while(rs.next()){
                results.add(new Product(rs.getInt("ProductId"), rs.getString("ProductName"), rs.getInt("CategoryId"), rs.getDouble("UnitPrice")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    @Override
    public List<Product> getByCategoryId(int categoryId) {

        List<Product> results = new ArrayList<>();

        String sql = "SELECT * FROM Products WHERE CategoryID = ?;";

        try (Connection c = dataSource.getConnection()) {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, categoryId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                results.add(new Product(rs.getInt("ProductID"), rs.getString("ProductName"), rs.getInt("CategoryID"), rs.getDouble("UnitPrice")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    @Override
    public List<Product> getByPrice(double productPrice) {
        List<Product> results = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE UnitPrice = ?;";

        try (Connection c = dataSource.getConnection()) {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setDouble(1, productPrice);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                results.add(new Product(rs.getInt("ProductID"), rs.getString("ProductName"), rs.getInt("CategoryID"), rs.getDouble("UnitPrice")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

}
