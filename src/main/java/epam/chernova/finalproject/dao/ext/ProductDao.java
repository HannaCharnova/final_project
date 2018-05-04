package epam.chernova.finalproject.dao.ext;

import epam.chernova.finalproject.connectionpool.ConnectionPool;
import epam.chernova.finalproject.dao.IProductDao;
import epam.chernova.finalproject.entity.Product;
import epam.chernova.finalproject.exception.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements IProductDao {
    private static final Logger LOGGER = Logger.getLogger(ProductDao.class);
    private static final String FIND_ALL_PRODUCTS = "SELECT * FROM menu";
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<Product> findAllProducts() throws DaoException {
        LOGGER.log(Level.DEBUG, "ProductDAO: Start find all products");
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<Product> products;
        try {
            statement = connection.prepareStatement(FIND_ALL_PRODUCTS);
            resultSet = statement.executeQuery();
            products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(createProductByResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            LOGGER.log(Level.DEBUG, "ProductDAO: Finish get all products");
            connectionPool.putBack(connection, resultSet, statement);
        }
        return products;
    }

    private Product createProductByResultSet(ResultSet resultSet) throws DaoException {
        Product product = new Product();
        try {
            product.setIdProduct(resultSet.getInt("idproduct"));
            product.setName(resultSet.getString("name"));
            product.setCost(resultSet.getDouble("cost"));
            product.setType(resultSet.getString("type"));
            product.setWeight(resultSet.getInt("weight"));
            product.setImagePath(resultSet.getString("image_path"));
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query",e);
        }
        return product;
    }



    @Override
    public void close(ResultSet resultSet, Statement statement) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
