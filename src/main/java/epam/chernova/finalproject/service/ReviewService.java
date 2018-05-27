package epam.chernova.finalproject.service;


import epam.chernova.finalproject.exception.ServiceException;

public interface ReviewService {
    void addReview(int idClient,String reviewText,int mark) throws ServiceException;
}

