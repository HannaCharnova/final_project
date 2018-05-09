package epam.chernova.finalproject.dao.ext;

import epam.chernova.finalproject.connectionpool.ConnectionPool;
import epam.chernova.finalproject.dao.IOrderDao;
import epam.chernova.finalproject.entity.Order;
import epam.chernova.finalproject.entity.Product;
import epam.chernova.finalproject.entity.ext.Client;
import epam.chernova.finalproject.exception.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements IOrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDao.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String FIND_ACTIVE_ORDER_BY_CLIENT_ID = "SELECT * FROM cafe.order WHERE cafe.order.client_user_iduser =? AND cafe.order.status=1";
    private static final String EDIT_ORDER_COST = "UPDATE cafe.order SET cafe.order.total_cost = (cafe.order.total_cost + ?) WHERE cafe.order.idorder = ?";
    private static final String ADD_ORDER = "INSERT INTO cafe.order (date,client_user_iduser,status) VALUES (CURRENT_DATE,?,1)";
    private static final String FIND_ORDERS_BY_CLIENT_ID = "SELECT * FROM cafe.order WHERE cafe.order.client_user_iduser =?";
    private static final String FIND_ORDER_BY_ORDER_ID = "SELECT * FROM cafe.order WHERE cafe.order.idorder =?";
    private static final String PAY_ORDER = "UPDATE cafe.order SET cafe.order.status = 0 WHERE cafe.order.idorder = ?";


    @Override
    public Order findActiveOrderByClientId(int idClient) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order DAO: start findActiveOrderByClientId");
        Order order = null;
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_ACTIVE_ORDER_BY_CLIENT_ID);
            statement.setInt(1, idClient);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                return createOrderByResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Order DAO: finish findActiveOrderByClientId");
            connectionPool.putBack(connection, resultSet, statement);
        }
        return null;
    }

    @Override
    public boolean editOrderCost(int idOrder, double deltaTotalCost) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order DAO: start editOrderCost");
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(EDIT_ORDER_COST);
            statement.setDouble(1, deltaTotalCost);
            statement.setInt(2, idOrder);
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query order editing", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Order DAO: finish editOrderCost");
            connectionPool.putBack(connection, resultSet, statement);
        }
        return false;
    }

    @Override
    public boolean addOrder(int idClient) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order DAO: start addOrder");
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(ADD_ORDER);
            statement.setInt(1, idClient);
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query order adding", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Order DAO: finish addOrder");
            connectionPool.putBack(connection, resultSet, statement);
        }
        return false;
    }

    @Override
    public List<Order> findAllOrdersByClientId(int idClient) throws DaoException {
        LOGGER.log(Level.DEBUG, "OrderDao: Start findAllOrdersByClientId.");
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<Order> orders;
        try {
            statement = connection.prepareStatement(FIND_ORDERS_BY_CLIENT_ID);
            statement.setInt(1, idClient);
            resultSet = statement.executeQuery();
            orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(createOrderByResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            LOGGER.log(Level.DEBUG, "OrderDao: Finish findAllOrdersByClientId.");
            connectionPool.putBack(connection, resultSet, statement);
        }
        return orders;

    }

    @Override
    public Order findOrderByOrderId(int idOrder) throws DaoException {
        LOGGER.log(Level.DEBUG, "OrderDao: Start findOrderByOrderId.");
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_ORDER_BY_ORDER_ID);
            statement.setInt(1, idOrder);
            resultSet = statement.executeQuery();
            while (resultSet.first()) {
                return createOrderByResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            LOGGER.log(Level.DEBUG, "OrderDao: Finish findOrderByOrderId.");
            connectionPool.putBack(connection, resultSet, statement);
        }
        return null;

    }

    @Override
    public void payOrder(int idOrder) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order DAO: start payOrder");
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(PAY_ORDER);
            statement.setInt(1, idOrder);
            if (statement.executeUpdate() != 0) {
                return;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query order adding", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Order DAO: finish payOrder");
            connectionPool.putBack(connection, resultSet, statement);
        }
    }

    private Order createOrderByResultSet(ResultSet resultSet) throws DaoException {
        Order order;
        try {
            order = new Order();
            order.setIdOrder(resultSet.getInt("idorder"));
            order.setDate(resultSet.getString("date"));
            order.setTotalCost(resultSet.getDouble("total_cost"));
            order.setIdClient(resultSet.getInt("client_user_iduser"));
            order.setStatus(resultSet.getBoolean("status"));
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return order;
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
