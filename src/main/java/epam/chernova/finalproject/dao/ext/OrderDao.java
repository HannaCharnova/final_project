package epam.chernova.finalproject.dao.ext;

import epam.chernova.finalproject.connectionpool.ConnectionPool;
import epam.chernova.finalproject.dao.IOrderDao;
import epam.chernova.finalproject.entity.Order;
import epam.chernova.finalproject.exception.ConnectionPoolException;
import epam.chernova.finalproject.exception.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements IOrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDao.class);
    private static final String FIND_ACTIVE_ORDER_BY_CLIENT_ID = "SELECT * FROM cafe.order WHERE cafe.order.client_user_iduser =? AND cafe.order.status=1";
    private static final String EDIT_ORDER_COST = "UPDATE cafe.order SET cafe.order.total_cost = (cafe.order.total_cost + ?) WHERE cafe.order.idorder = ?";
    private static final String ADD_ORDER = "INSERT INTO cafe.order (date,client_user_iduser,status) VALUES (CURRENT_DATE,?,1)";
    private static final String FIND_ORDERS_BY_CLIENT_ID = "SELECT * FROM cafe.order WHERE cafe.order.client_user_iduser =?";
    private static final String FIND_ORDER_BY_ORDER_ID = "SELECT * FROM cafe.order WHERE cafe.order.idorder =?";
    private static final String PAY_ORDER = "UPDATE cafe.order SET cafe.order.status = 0 WHERE cafe.order.idorder = ?";
    private static final String FIND_ALL_ORDERS = "SELECT * FROM cafe.order";
    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;


    @Override
    public Order findActiveOrderByClientId(int idClient) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order DAO: start findActiveOrderByClientId");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_ACTIVE_ORDER_BY_CLIENT_ID);
                statement.setInt(1, idClient);
                resultSet = statement.executeQuery();
                if (resultSet.first()) {
                    return createOrderByResultSet(resultSet);
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Order DAO: finish findActiveOrderByClientId");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return null;
    }

    @Override
    public boolean editOrderCost(int idOrder, double deltaTotalCost) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order DAO: start editOrderCost");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(EDIT_ORDER_COST);
                statement.setDouble(1, deltaTotalCost);
                statement.setInt(2, idOrder);
                if (statement.executeUpdate() != 0) {
                    return true;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Order DAO: finish editOrderCost");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query order editing", e);
        }
        return false;
    }

    @Override
    public boolean addOrder(int idClient) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order DAO: start addOrder");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(ADD_ORDER);
                statement.setInt(1, idClient);
                if (statement.executeUpdate() != 0) {
                    return true;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Order DAO: finish addOrder");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query order adding", e);
        }
        return false;
    }

    @Override
    public List<Order> findAllOrdersByClientId(int idClient) throws DaoException {
        LOGGER.log(Level.DEBUG, "OrderDao: Start findAllOrdersByClientId.");
        List<Order> orders;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_ORDERS_BY_CLIENT_ID);
                statement.setInt(1, idClient);
                resultSet = statement.executeQuery();
                orders = new ArrayList<>();
                while (resultSet.next()) {
                    orders.add(createOrderByResultSet(resultSet));
                }
            } finally {
                LOGGER.log(Level.DEBUG, "OrderDao: Finish findAllOrdersByClientId.");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        }
        return orders;

    }

    @Override
    public List<Order> findAllOrders() throws DaoException {
        LOGGER.log(Level.DEBUG, "OrderDao: Start findAllOrders.");
        List<Order> orders;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_ALL_ORDERS);
                resultSet = statement.executeQuery();
                orders = new ArrayList<>();
                while (resultSet.next()) {
                    orders.add(createOrderByResultSet(resultSet));
                }
            } finally {
                LOGGER.log(Level.DEBUG, "OrderDao: Finish findAllOrders.");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        }
        return orders;

    }


    @Override
    public Order findOrderByOrderId(int idOrder) throws DaoException {
        LOGGER.log(Level.DEBUG, "OrderDao: Start findOrderByOrderId.");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_ORDER_BY_ORDER_ID);
                statement.setInt(1, idOrder);
                resultSet = statement.executeQuery();
                while (resultSet.first()) {
                    return createOrderByResultSet(resultSet);
                }
            } finally {
                LOGGER.log(Level.DEBUG, "OrderDao: Finish findOrderByOrderId.");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        }
        return null;

    }

    @Override
    public void payOrder(int idOrder) throws DaoException {
        LOGGER.log(Level.DEBUG, "Order DAO: start payOrder");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(PAY_ORDER);
                statement.setInt(1, idOrder);
                if (statement.executeUpdate() != 0) {
                    return;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Order DAO: finish payOrder");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query order adding", e);
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
}
