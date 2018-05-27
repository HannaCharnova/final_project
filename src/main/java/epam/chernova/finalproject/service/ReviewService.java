package epam.chernova.finalproject.service;


import epam.chernova.finalproject.entity.Review;
import epam.chernova.finalproject.exception.ServiceException;

import java.util.List;

public interface ReviewService {
    void addReview(int idClient,String reviewText,int mark) throws ServiceException;

    List<Review> findAllReviews() throws ServiceException;

}

