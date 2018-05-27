package epam.chernova.finalproject.dao.ext;

import epam.chernova.finalproject.connectionpool.ConnectionPool;
import epam.chernova.finalproject.dao.IAccountDao;
import epam.chernova.finalproject.dao.IReviewDao;
import epam.chernova.finalproject.entity.Account;
import epam.chernova.finalproject.exception.ConnectionPoolException;
import epam.chernova.finalproject.exception.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewDao implements IReviewDao {
    private static final String ADD_REVIEW = "INSERT INTO cafe.review (review_text,mark,client_user_iduser) VALUES (?,?,?)";
    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;

    @Override
    public void addReview(int idClient, String reviewText, int mark) throws DaoException {
        LOGGER.log(Level.DEBUG, "Review DAO: start addReview");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(ADD_REVIEW);
            statement.setString(1, reviewText);
            statement.setInt(2, mark);
            statement.setInt(3, idClient);
            if (statement.executeUpdate() != 0) {
                return;
            }
        } catch (SQLException| ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Review DAO: finish addReview");
            close(resultSet, statement);
            connectionPool.putBackConnection(connection);
        }

    }
}
