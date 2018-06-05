package epam.chernova.finalproject.dao.ext;

import epam.chernova.finalproject.connectionpool.ConnectionPool;
import epam.chernova.finalproject.dao.IOrderProductDao;
import epam.chernova.finalproject.entity.Order;
import epam.chernova.finalproject.entity.OrderProduct;
import epam.chernova.finalproject.entity.Product;
import epam.chernova.finalproject.exception.ConnectionPoolException;
import epam.chernova.finalproject.exception.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderProductDao implements IOrderProductDao {
    private static final Logger LOGGER = Logger.getLogger(OrderProductDao.class);
    private static final String ADD_ORDER_PRODUCT = "INSERT INTO cafe.order_product (order_product.order_idorder,order_product.product_idproduct,order_product.quantity) VALUES (?,?,?)";
    private static final String FIND_ORDER_PRODUCT_BY_CLIENT_ID = "SELECT * FROM cafe.order_product JOIN cafe.order ON cafe.order.idorder = cafe.order_product.order_idorder WHERE cafe.order.client_user_iduser =?";
    private static final String REMOVE_ORDER_PRODUCT = "DELETE FROM cafe.order_product WHERE cafe.order_product.order_idorder=? AND cafe.order_product.product_idproduct=?";
    private static final String DELETE_ORDER_PRODUCT = "DELETE FROM cafe.order_product WHERE cafe.order_product.order_idorder=?";
    private static final String FIND_ORDER_PRODUCT_BY_PRODUCT_ID = "SELECT * FROM cafe.order_product WHERE cafe.order_product.product_idproduct =?";
    private static final String FIND_ALL_ORDER_PRODUCT = "SELECT * FROM cafe.order_product";
    private static final String FIND_PRODUCT_IN_ACTIVE_ORDER = "SELECT * FROM cafe.order_product WHERE cafe.order_product.product_idproduct =? AND cafe.order_product.order_idorder=?";
    private static final String ADD_ORDER_PRODUCT_QUANTITY = "UPDATE cafe.order_product SET cafe.order_product.quantity = (cafe.order_product.quantity+?) WHERE cafe.order_product.product_idproduct = ? AND cafe.order_product.order_idorder=?";
    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;

    @Override
    public void addOrderProduct(int idOrder, int idProduct, int quantity) throws DaoException {
        LOGGER.log(Level.DEBUG, "OrderProduct DAO: start addOrderProduct");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(ADD_ORDER_PRODUCT);
                statement.setInt(1, idOrder);
                statement.setInt(2, idProduct);
                statement.setInt(3, quantity);
                if (statement.executeUpdate() != 0) {
                    return;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "OrderProduct DAO: finish addOrderProduct");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
    }

    @Override
    public void removeOrderProduct(int idOrder, int idProduct) throws DaoException {
        LOGGER.log(Level.DEBUG, "OrderProduct DAO: start removeOrderProduct");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(REMOVE_ORDER_PRODUCT);
                statement.setInt(1, idOrder);
                statement.setInt(2, idProduct);
                if (statement.executeUpdate() != 0) {
                    return;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "OrderProduct DAO: finish removeOrderProduct");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
    }

    @Override
    public void deleteOrderProduct(int idOrder) throws DaoException {
        LOGGER.log(Level.DEBUG, "OrderProduct DAO: start deleteOrderProduct");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(DELETE_ORDER_PRODUCT);
                statement.setInt(1, idOrder);
                if (statement.executeUpdate() != 0) {
                    return;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "OrderProduct DAO: finish deleteOrderProduct");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }

    }

    @Override
    public List<OrderProduct> findOrderProductsByClientId(int idClient) throws DaoException {
        LOGGER.log(Level.DEBUG, "OrderProductDAO: start findActiveOrderByClientId");
        List<OrderProduct> orderProducts = new ArrayList<>();
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_ORDER_PRODUCT_BY_CLIENT_ID);
                statement.setInt(1, idClient);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    orderProducts.add(createOrderProductByResultSet(resultSet));
                }
            } finally {
                LOGGER.log(Level.DEBUG, "OrderProductDAO: finish findActiveOrderByClientId");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return orderProducts;

    }

    @Override
    public List<OrderProduct> findAllOrderProducts() throws DaoException {
        LOGGER.log(Level.DEBUG, "OrderProductDAO: start findAllOrderProducts");
        List<OrderProduct> orderProducts = new ArrayList<>();
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_ALL_ORDER_PRODUCT);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    orderProducts.add(createOrderProductByResultSet(resultSet));
                }
            } finally {
                LOGGER.log(Level.DEBUG, "OrderProductDAO: finish findAllOrderProducts");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return orderProducts;

    }


    @Override
    public boolean checkActiveOrderProduct(int idProduct) throws DaoException {
        LOGGER.log(Level.DEBUG, "OrderProduct DAO: start checkActiveOrderProduct");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_ORDER_PRODUCT_BY_PRODUCT_ID);
                statement.setInt(1, idProduct);
                resultSet = statement.executeQuery();
                if (resultSet.first()) {
                    return true;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "OrderProduct DAO: finish checkActiveOrderProduct");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return false;
    }

    @Override
    public OrderProduct checkProductInActiveOrder(int idOrder, int idProduct) throws DaoException {
        LOGGER.log(Level.DEBUG, "OrderProduct DAO: start checkProductInActiveOrder");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_PRODUCT_IN_ACTIVE_ORDER);
                statement.setInt(1, idProduct);
                statement.setInt(2, idOrder);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    return createOrderProductByResultSet(resultSet);
                }
            } finally {
                LOGGER.log(Level.DEBUG, "OrderProduct DAO: finish checkProductInActiveOrder");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return null;
    }

    @Override
    public void addOrderProductQuantity(int idOrder, int idProduct, int quantity) throws DaoException {
        LOGGER.log(Level.DEBUG, "OrderProduct DAO: start addOrderProductQuantity");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(ADD_ORDER_PRODUCT_QUANTITY);
                statement.setInt(1, quantity);
                statement.setInt(2, idProduct);
                statement.setInt(3, idOrder);
                if (statement.executeUpdate() != 0) {
                    return;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "OrderProduct DAO: finish addOrderProductQuantity");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
    }

    private OrderProduct createOrderProductByResultSet(ResultSet resultSet) throws DaoException {
        OrderProduct orderProduct = new OrderProduct();
        try {
            orderProduct.setIdOrder(resultSet.getInt("order_idorder"));
            orderProduct.setIdProduct(resultSet.getInt("product_idproduct"));
            orderProduct.setQuantity(resultSet.getInt("quantity"));
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return orderProduct;
    }
}
