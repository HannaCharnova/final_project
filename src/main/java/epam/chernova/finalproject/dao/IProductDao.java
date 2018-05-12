package epam.chernova.finalproject.dao;

import epam.chernova.finalproject.entity.Product;
import epam.chernova.finalproject.entity.User;
import epam.chernova.finalproject.entity.ext.Client;
import epam.chernova.finalproject.exception.DaoException;
import epam.chernova.finalproject.exception.ServiceException;

import java.util.List;

public interface IProductDao extends AbstractDao {
    List<Product> findAllProducts() throws DaoException;

    List<Product> findProductByType(String productType) throws DaoException;

    Product findProductById(int idProduct) throws DaoException;

    List<Product> findExistProducts() throws DaoException;

    void deleteProduct(int idProduct) throws ServiceException, DaoException;

}
