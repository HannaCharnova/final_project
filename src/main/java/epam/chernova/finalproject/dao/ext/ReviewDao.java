package epam.chernova.finalproject.dao.ext;

import epam.chernova.finalproject.connectionpool.ConnectionPool;
import epam.chernova.finalproject.dao.IAccountDao;
import epam.chernova.finalproject.dao.IReviewDao;
import epam.chernova.finalproject.entity.Account;
import epam.chernova.finalproject.entity.Order;
import epam.chernova.finalproject.entity.Review;
import epam.chernova.finalproject.exception.ConnectionPoolException;
import epam.chernova.finalproject.exception.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao implements IReviewDao {
    private static final String ADD_REVIEW = "INSERT INTO cafe.review (review_text,mark,client_user_iduser) VALUES (?,?,?)";
    private static final String FIND_ALL_REVIEWS = "SELECT * FROM cafe.review";
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
            try {
                statement = connection.prepareStatement(ADD_REVIEW);
                statement.setString(1, reviewText);
                statement.setInt(2, mark);
                statement.setInt(3, idClient);
                if (statement.executeUpdate() != 0) {
                    return;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Review DAO: finish addReview");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }

    }

    @Override
    public List<Review> findAllReviews() throws DaoException {
        LOGGER.log(Level.DEBUG, "Review DAO: Start findAllReviews.");
        List<Review> reviews;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_ALL_REVIEWS);
                resultSet = statement.executeQuery();
                reviews = new ArrayList<>();
                while (resultSet.next()) {
                    reviews.add(createReviewByResultSet(resultSet));
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Review DAO: Finish findAllReviews.");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        }
        return reviews;

    }

    private Review createReviewByResultSet(ResultSet resultSet) throws DaoException {
        Review review;
        try {
            review = new Review();
            review.setIdReview(resultSet.getInt("idreview"));
            review.setReviewText(resultSet.getString("review_text"));
            review.setMark(resultSet.getInt("mark"));
            review.setIdClient(resultSet.getInt("client_user_iduser"));
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return review;
    }

}
