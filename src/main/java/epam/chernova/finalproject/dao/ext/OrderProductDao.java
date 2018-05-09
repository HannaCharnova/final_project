package epam.chernova.finalproject.dao.ext;

import epam.chernova.finalproject.connectionpool.ConnectionPool;
import epam.chernova.finalproject.dao.IOrderProductDao;
import epam.chernova.finalproject.exception.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;

public class OrderProductDao implements IOrderProductDao {
    private static final Logger LOGGER = Logger.getLogger(OrderProductDao.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String ADD_ORDER_PRODUCT = "INSERT INTO cafe.order_product (order_product.order_idorder,order_product.product_idproduct,order_product.quantity) VALUES (?,?,?)";

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
