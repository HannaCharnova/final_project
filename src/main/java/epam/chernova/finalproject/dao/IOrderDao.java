package epam.chernova.finalproject.dao;


import epam.chernova.finalproject.entity.Order;
import epam.chernova.finalproject.exception.DaoException;

import java.util.List;

public interface IOrderDao extends AbstractDao {
    Order findActiveOrderByClientId(int idClient) throws DaoException;

    boolean editOrderCost(int idOrder,double deltaTotalCost) throws DaoException;

    boolean addOrder(int idClient) throws DaoException;

    List<Order> findAllOrdersByClientId(int idClient) throws DaoException;

    Order findOrderByOrderId(int idOrder) throws DaoException;

    void payOrder(int idOrder) throws DaoException;

}
