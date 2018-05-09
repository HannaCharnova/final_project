package epam.chernova.finalproject.dao;


import epam.chernova.finalproject.exception.DaoException;

public interface IOrderProductDao extends AbstractDao {

    void addOrderProduct(int idOrder, int idProduct, int quantity) throws DaoException;


}
