package epam.chernova.finalproject.dao.ext;

import epam.chernova.finalproject.connectionpool.ConnectionPool;
import epam.chernova.finalproject.dao.IProductDao;
import epam.chernova.finalproject.entity.Order;
import epam.chernova.finalproject.entity.Product;
import epam.chernova.finalproject.entity.ext.Client;
import epam.chernova.finalproject.exception.DaoException;
import epam.chernova.finalproject.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements IProductDao {
    private static final Logger LOGGER = Logger.getLogger(ProductDao.class);
    private static final String FIND_ALL_PRODUCTS = "SELECT * FROM menu";
    private static final String FIND_EXIST_PRODUCTS = "SELECT * FROM menu WHERE exist=1 ";
    private static final String FIND_PRODUCT_BY_TYPE = "SELECT * FROM menu WHERE type=? AND exist=1";
    private static final String FIND_PRODUCT_BY_ID = "SELECT * FROM menu WHERE idproduct=?";
    private static final String DELETE_PRODUCT = "UPDATE cafe.menu SET cafe.menu.exist = 0 WHERE cafe.menu.idproduct = ?";
    private static final String FIND_PRODUCT_BY_NAME = "SELECT * FROM menu WHERE menu.name_en=? OR menu.name_ru=?";
    private static final String ADD_PRODUCT = "INSERT INTO menu (name_en,name_ru,type,cost,weight,image_path,exist) VALUES (?,?,?,?,?,?,1)";
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

    @Override
    public List<Product> findExistProducts() throws DaoException {
        LOGGER.log(Level.DEBUG, "ProductDAO: Start find exist products");
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<Product> products;
        try {
            statement = connection.prepareStatement(FIND_EXIST_PRODUCTS);
            resultSet = statement.executeQuery();
            products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(createProductByResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            LOGGER.log(Level.DEBUG, "ProductDAO: Finish find exist products");
            connectionPool.putBack(connection, resultSet, statement);
        }
        return products;
    }

    @Override
    public void deleteProduct(int idProduct) throws DaoException {
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        LOGGER.log(Level.DEBUG, "Product DAO: start deleteProduct");
        try {
            statement = connection.prepareStatement(DELETE_PRODUCT);
            statement.setInt(1, idProduct);
            if (statement.executeUpdate() != 0) {
                return;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "OrderProduct DAO: finish deleteProduct");
            connectionPool.putBack(connection, resultSet, statement);
        }

    }

    @Override
    public Product findProductByName(String nameEn, String nameRu) throws DaoException {
        LOGGER.log(Level.DEBUG, "ProductDAO: Start find product by name.");
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Product product=null;
        try {
            statement = connection.prepareStatement(FIND_PRODUCT_BY_NAME);
            statement.setString(1, nameEn);
            statement.setString(2, nameRu);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                product=createProductByResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            LOGGER.log(Level.DEBUG, "ProductDAO: Finish find product by name.");
            connectionPool.putBack(connection, resultSet, statement);
        }
        System.out.println(product);
        return product;

    }

    @Override
    public void addProduct(String type, String nameEn, String nameRu, double cost, double weight, String imagePath) throws DaoException {
        LOGGER.log(Level.DEBUG, "Product Dao: start addProduct");
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(ADD_PRODUCT);
            statement.setString(1, nameEn);
            statement.setString(2, nameRu);
            statement.setString(3, type);
            statement.setDouble(4, cost);
            statement.setDouble(5, weight);
            statement.setString(6, imagePath);
            if (statement.executeUpdate() != 0) {
                return;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Product Dao: finish addProduct");
            connectionPool.putBack(connection, resultSet, statement);
        }
    }


    @Override
    public List<Product> findProductByType(String productType) throws DaoException {
        LOGGER.log(Level.DEBUG, "ProductDAO: Start find product by type.");
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<Product> products;
        try {
            statement = connection.prepareStatement(FIND_PRODUCT_BY_TYPE);
            statement.setString(1, productType);
            resultSet = statement.executeQuery();
            products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(createProductByResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            LOGGER.log(Level.DEBUG, "ProductDAO: Finish find product by type.");
            connectionPool.putBack(connection, resultSet, statement);
        }
        return products;

    }

    @Override
    public Product findProductById(int idProduct) throws DaoException {
        LOGGER.log(Level.DEBUG, "ProductDAO: Start find product by id.");
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Product product;
        try {
            statement = connection.prepareStatement(FIND_PRODUCT_BY_ID);
            statement.setInt(1, idProduct);
            resultSet = statement.executeQuery();
            product = new Product();
            while (resultSet.next()) {
                product=createProductByResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            LOGGER.log(Level.DEBUG, "ProductDAO: Finish find product by id.");
            connectionPool.putBack(connection, resultSet, statement);
        }
        return product;

    }

    private Product createProductByResultSet(ResultSet resultSet) throws DaoException {
        Product product = new Product();
        try {
            product.setIdProduct(resultSet.getInt("idproduct"));
            product.setNameEn(resultSet.getString("name_en"));
            product.setNameRu(resultSet.getString("name_ru"));
            product.setCost(resultSet.getDouble("cost"));
            product.setType(resultSet.getString("type"));
            product.setWeight(resultSet.getInt("weight"));
            product.setImagePath(resultSet.getString("image_path"));
            product.setExist(resultSet.getBoolean("exist"));
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
