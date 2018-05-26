package epam.chernova.finalproject.dao;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface AbstractDao {
    Logger LOGGER = Logger.getLogger(AbstractDao.class);

    default void close(ResultSet resultSet, Statement statement) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, e.getMessage());
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, e.getMessage());
            }
        }
    }
}
