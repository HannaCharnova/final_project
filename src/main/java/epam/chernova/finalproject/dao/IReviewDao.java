package epam.chernova.finalproject.dao;


import epam.chernova.finalproject.entity.Review;
import epam.chernova.finalproject.exception.DaoException;

import java.util.List;

public interface IReviewDao extends AbstractDao {
    void addReview(int idClient,String reviewText,int mark) throws DaoException;

    List<Review> findAllReviews() throws DaoException;

}
