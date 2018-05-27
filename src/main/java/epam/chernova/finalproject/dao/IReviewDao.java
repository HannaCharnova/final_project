package epam.chernova.finalproject.dao;


import epam.chernova.finalproject.exception.DaoException;

public interface IReviewDao extends AbstractDao {
    void addReview(int idClient,String reviewText,int mark) throws DaoException;

}
