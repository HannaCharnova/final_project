package epam.chernova.finalproject.dao.ext;

import epam.chernova.finalproject.connectionpool.ConnectionPool;
import epam.chernova.finalproject.dao.IOrderDao;
import epam.chernova.finalproject.entity.Order;
import epam.chernova.finalproject.entity.ext.Client;
import epam.chernova.finalproject.exception.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;

public class OrderDao implements IOrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDao.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String FIND_ACTIVE_ORDER_BY_CLIENT_ID = "SELECT * FROM cafe.order WHERE cafe.order.client_user_iduser =? AND cafe.order.status=1";
    private static final String EDIT_ORDER_COST = "UPDATE cafe.order SET cafe.order.total_cost = (cafe.order.total_cost + ?) WHERE cafe.order.idorder = ?";


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
                order = new Order();
                order.setIdOrder(resultSet.getInt("idorder"));
                order.setDate(resultSet.getString("date"));
                order.setTotalCost(resultSet.getDouble("total_cost"));
                order.setIdClient(resultSet.getInt("client_user_iduser"));
                order.setStatus(resultSet.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Order DAO: finish findClientByEmail");
            connectionPool.putBack(connection, resultSet, statement);
        }
        return order;
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
