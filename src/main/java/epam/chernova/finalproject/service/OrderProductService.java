package epam.chernova.finalproject.service;


import epam.chernova.finalproject.exception.ServiceException;

public interface OrderProductService {
    void addOrderProduct(int idOrder,int idProduct,int quantity) throws ServiceException;
}
