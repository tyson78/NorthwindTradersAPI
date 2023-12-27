package com.pluralsight.northwindtraders.data;

import com.pluralsight.northwindtraders.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// JDBC implementation of ProductDao interface to communicate with database
@Component // @Component indicates this class is injectable somewhere else by @Autowired, usually in Controller class
@Primary
public class JdbcProductDao implements ProductDao {

    private DataSource dataSource;

    @Autowired // injecting dataSource bean here from dataSource() in DatabaseConfig
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

    @Override
    public Product insert(Product product) {
        Product result = null;

        String sql = """
                INSERT INTO Products (ProductName, CategoryID, UnitPrice)
                VALUES (?, ?, ?);
                """;

        try (Connection c = dataSource.getConnection()) {
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getProductName());
            ps.setInt(2, product.getCategoryId());
            ps.setDouble(3, product.getUnitPrice());

            ps.executeUpdate(); // returns number of records that were affected

            try (ResultSet rs = ps.getGeneratedKeys()) {
                while (rs.next()) {
                    result = new Product(rs.getInt(1), product.getProductName(), product.getCategoryId(), product.getUnitPrice());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    // REST method: PUT
    // updating a record in database
    @Override
    public void update(int productID, Product product) {
        String sql = """
                UPDATE Products
                SET ProductName = ?, UnitPrice = ?
                WHERE ProductID = ?;
                """;
        try (Connection c = dataSource.getConnection()) {
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getProductName());
            ps.setDouble(2, product.getUnitPrice());
            ps.setInt(3, productID);

            ps.executeUpdate(); // returns number of records that were affected

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // REST method: DELETE
    // Deleting a record in database
    @Override
    public void delete(int productID) {
        String sql = """
                DELETE FROM Products
                WHERE ProductID = ?;
                """;
        try (Connection c = dataSource.getConnection()) {
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, productID);

            ps.executeUpdate();
            // Executes the given SQL statement, which may be an INSERT, UPDATE, or DELETE statement
            // or an SQL statement that returns nothing, such as an SQL DDL statement.

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
