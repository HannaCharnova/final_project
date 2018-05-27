package epam.chernova.finalproject.dao.ext;

import epam.chernova.finalproject.connectionpool.ConnectionPool;
import epam.chernova.finalproject.dao.IProductDao;
import epam.chernova.finalproject.entity.Product;
import epam.chernova.finalproject.exception.ConnectionPoolException;
import epam.chernova.finalproject.exception.DaoException;
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
    private static final String FIND_PRODUCT_BY_NAME_AND_ID = "SELECT * FROM menu WHERE (menu.name_en=? OR menu.name_ru=?) AND menu.idproduct!=?";
    private static final String ADD_PRODUCT = "INSERT INTO menu (name_en,name_ru,type,cost,weight,image_path,exist) VALUES (?,?,?,?,?,?,1)";
    private static final String EDIT_PRODUCT = "UPDATE cafe.menu SET cafe.menu.name_en = ?,cafe.menu.name_ru=?,cafe.menu.type=?,cafe.menu.cost=?,cafe.menu.weight=?,cafe.menu.image_path=? WHERE cafe.menu.idproduct = ?";
    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;


    @Override
    public List<Product> findAllProducts() throws DaoException {
        LOGGER.log(Level.DEBUG, "ProductDAO: Start find all products");
        List<Product> products;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_ALL_PRODUCTS);
                resultSet = statement.executeQuery();
                products = new ArrayList<>();
                while (resultSet.next()) {
                    products.add(createProductByResultSet(resultSet));
                }
            } finally {
                LOGGER.log(Level.DEBUG, "ProductDAO: Finish get all products");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        }
        return products;
    }

    @Override
    public List<Product> findExistProducts() throws DaoException {
        LOGGER.log(Level.DEBUG, "ProductDAO: Start find exist products");
        List<Product> products;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_EXIST_PRODUCTS);
                resultSet = statement.executeQuery();
                products = new ArrayList<>();
                while (resultSet.next()) {
                    products.add(createProductByResultSet(resultSet));
                }
            } finally {
                LOGGER.log(Level.DEBUG, "ProductDAO: Finish find exist products");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        }
        return products;
    }

    @Override
    public void deleteProduct(int idProduct) throws DaoException {
        LOGGER.log(Level.DEBUG, "Product DAO: start deleteProduct");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(DELETE_PRODUCT);
                statement.setInt(1, idProduct);
                if (statement.executeUpdate() != 0) {
                    return;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "OrderProduct DAO: finish deleteProduct");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }

    }

    @Override
    public Product findProductByName(String nameEn, String nameRu) throws DaoException {
        LOGGER.log(Level.DEBUG, "ProductDAO: Start find product by name.");
        Product product = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_PRODUCT_BY_NAME);
                statement.setString(1, nameEn);
                statement.setString(2, nameRu);
                resultSet = statement.executeQuery();
                if (resultSet.first()) {
                    product = createProductByResultSet(resultSet);
                }
            } finally {
                LOGGER.log(Level.DEBUG, "ProductDAO: Finish find product by name.");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        }
        System.out.println(product);
        return product;

    }

    @Override
    public void addProduct(String type, String nameEn, String nameRu, double cost, double weight, String imagePath) throws
            DaoException {
        LOGGER.log(Level.DEBUG, "Product Dao: start addProduct");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
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
            } finally {
                LOGGER.log(Level.DEBUG, "Product Dao: finish addProduct");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
    }

    @Override
    public Product findProductByNameAndId(String nameEn, String nameRu, int idProduct) throws DaoException {
        LOGGER.log(Level.DEBUG, "ProductDAO: Start findProductByNameAndId.");
        Product product = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_PRODUCT_BY_NAME_AND_ID);
                statement.setString(1, nameEn);
                statement.setString(2, nameRu);
                statement.setInt(3, idProduct);
                resultSet = statement.executeQuery();
                if (resultSet.first()) {
                    product = createProductByResultSet(resultSet);
                }
            } finally {
                LOGGER.log(Level.DEBUG, "ProductDAO: Finish findProductByNameAndId.");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        }
        return product;
    }

    @Override
    public void editProduct(int idProduct, String type, String nameEn, String nameRu, double cost,
                            double weight, String imagePath) throws DaoException {
        LOGGER.log(Level.DEBUG, "Product Dao: start editProduct");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(EDIT_PRODUCT);
                statement.setString(1, nameEn);
                statement.setString(2, nameRu);
                statement.setString(3, type);
                statement.setDouble(4, cost);
                statement.setDouble(5, weight);
                statement.setString(6, imagePath);
                statement.setInt(7, idProduct);
                if (statement.executeUpdate() != 0) {
                    return;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Product Dao: finish editProduct");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
    }


    @Override
    public List<Product> findProductByType(String productType) throws DaoException {
        LOGGER.log(Level.DEBUG, "ProductDAO: Start find product by type.");
        List<Product> products;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_PRODUCT_BY_TYPE);
                statement.setString(1, productType);
                resultSet = statement.executeQuery();
                products = new ArrayList<>();
                while (resultSet.next()) {
                    products.add(createProductByResultSet(resultSet));
                }
            } finally {
                LOGGER.log(Level.DEBUG, "ProductDAO: Finish find product by type.");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        }
        return products;

    }

    @Override
    public Product findProductById(int idProduct) throws DaoException {
        LOGGER.log(Level.DEBUG, "ProductDAO: Start find product by id.");
        Product product;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_PRODUCT_BY_ID);
                statement.setInt(1, idProduct);
                resultSet = statement.executeQuery();
                product = new Product();
                while (resultSet.next()) {
                    product = createProductByResultSet(resultSet);
                }
            } finally {
                LOGGER.log(Level.DEBUG, "ProductDAO: Finish find product by id.");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
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
            throw new DaoException("Exception while executing SQL query", e);
        }
        return product;
    }


}
