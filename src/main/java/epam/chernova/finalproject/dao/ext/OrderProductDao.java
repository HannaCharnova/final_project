package epam.chernova.finalproject.dao.ext;

import epam.chernova.finalproject.connectionpool.ConnectionPool;
import epam.chernova.finalproject.dao.IOrderProductDao;
import epam.chernova.finalproject.entity.Order;
import epam.chernova.finalproject.entity.OrderProduct;
import epam.chernova.finalproject.entity.Product;
import epam.chernova.finalproject.exception.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderProductDao implements IOrderProductDao {
    private static final Logger LOGGER = Logger.getLogger(OrderProductDao.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String ADD_ORDER_PRODUCT = "INSERT INTO cafe.order_product (order_product.order_idorder,order_product.product_idproduct,order_product.quantity) VALUES (?,?,?)";
    private static final String FIND_ORDER_PRODUCT_BY_CLIENT_ID = "SELECT * FROM cafe.order_product JOIN cafe.order ON cafe.order.idorder = cafe.order_product.order_idorder WHERE cafe.order.client_user_iduser =?";
    private static final String REMOVE_ORDER_PRODUCT = "DELETE FROM cafe.order_product WHERE cafe.order_product.order_idorder=? AND cafe.order_product.product_idproduct=?";

    @Override
    public void addOrderProduct(int idOrder, int idProduct, int quantity) throws DaoException {
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        LOGGER.log(Level.DEBUG, "OrderProduct DAO: start addOrderProduct");
        try {
            statement = connection.prepareStatement(ADD_ORDER_PRODUCT);
            statement.setInt(1, idOrder);
            statement.setInt(2, idProduct);
            statement.setInt(3, quantity);
            if (statement.executeUpdate() != 0) {
                return;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "OrderProduct DAO: finish addOrderProduct");
            connectionPool.putBack(connection, resultSet, statement);
        }
    }

    @Override
    public void removeOrderProduct(int idOrder, int idProduct) throws DaoException {
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        LOGGER.log(Level.DEBUG, "OrderProduct DAO: start removeOrderProduct");
        try {
            statement = connection.prepareStatement(REMOVE_ORDER_PRODUCT);
            statement.setInt(1, idOrder);
            statement.setInt(2, idProduct);
            if (statement.executeUpdate() != 0) {
                return;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "OrderProduct DAO: finish removeOrderProduct");
            connectionPool.putBack(connection, resultSet, statement);
        }
    }

    @Override
    public List<OrderProduct> findOrderProductsByClientId(int idClient) throws DaoException {
        LOGGER.log(Level.DEBUG, "OrderProductDAO: start findActiveOrderByClientId");
        List<OrderProduct> orderProducts = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_ORDER_PRODUCT_BY_CLIENT_ID);
            statement.setInt(1, idClient);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orderProducts.add(createOrderProductByResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "OrderProductDAO: finish findActiveOrderByClientId");
            connectionPool.putBack(connection, resultSet, statement);
        }
        return orderProducts;

    }

    private OrderProduct createOrderProductByResultSet(ResultSet resultSet) throws DaoException {
        OrderProduct orderProduct = new OrderProduct();
        try {
            orderProduct.setIdOrder(resultSet.getInt("order_idorder"));
            orderProduct.setIdProduct(resultSet.getInt("product_idproduct"));
            orderProduct.setQuantity(resultSet.getInt("quantity"));
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query",e);
        }
        return orderProduct;
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
