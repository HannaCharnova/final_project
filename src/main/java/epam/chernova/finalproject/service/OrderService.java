package epam.chernova.finalproject.service;


import epam.chernova.finalproject.entity.Order;
import epam.chernova.finalproject.exception.ServiceException;

public interface OrderService {
    Order findActiveOrderByClientId(int idClient) throws ServiceException;

    void editOrderCost(int idOrder, double deltaTotalCost) throws ServiceException;
}
