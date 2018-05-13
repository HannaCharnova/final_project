package epam.chernova.finalproject.dao;


import epam.chernova.finalproject.entity.OrderProduct;
import epam.chernova.finalproject.exception.DaoException;
import epam.chernova.finalproject.exception.ServiceException;

import java.util.List;

public interface IOrderProductDao extends AbstractDao {

    void addOrderProduct(int idOrder, int idProduct, int quantity) throws DaoException;

    void removeOrderProduct(int idOrder, int idProduct) throws DaoException;

    List<OrderProduct> findOrderProductsByClientId(int idClient) throws DaoException;

    List<OrderProduct> findAllOrderProducts() throws DaoException;

    boolean checkActiveOrderProduct(int idProduct) throws DaoException;

}
