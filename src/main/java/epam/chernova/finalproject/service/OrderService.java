package epam.chernova.finalproject.service;


import epam.chernova.finalproject.entity.Order;
import epam.chernova.finalproject.exception.ServiceException;

import java.util.List;

public interface OrderService {
    Order findActiveOrderByClientId(int idClient) throws ServiceException;

    void editOrderCost(int idOrder, double deltaTotalCost) throws ServiceException;

    void addOrder(int idClient) throws ServiceException;

    List<Order> findAllOrdersByClientId(int idClient) throws ServiceException;

    List<Order> findAllOrders() throws ServiceException;

    Order findOrderByOrderId(int idOrder) throws ServiceException;

    void payOrder(int idOrder) throws ServiceException;

}
