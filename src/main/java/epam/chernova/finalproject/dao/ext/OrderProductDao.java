package epam.chernova.finalproject.dao.ext;

import epam.chernova.finalproject.dao.IOrderProductDao;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderProductDao implements IOrderProductDao {
    private static final Logger LOGGER = Logger.getLogger(OrderProductDao.class);


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
