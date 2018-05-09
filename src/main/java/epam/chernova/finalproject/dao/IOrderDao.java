package epam.chernova.finalproject.dao;


import epam.chernova.finalproject.entity.Order;
import epam.chernova.finalproject.exception.DaoException;

public interface IOrderDao extends AbstractDao {
    Order findActiveOrderByClientId(int idClient) throws DaoException;

    boolean editOrderCost(int idOrder,double deltaTotalCost) throws DaoException;
}
