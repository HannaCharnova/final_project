package epam.chernova.finalproject.dao;

import epam.chernova.finalproject.entity.Product;
import epam.chernova.finalproject.entity.User;
import epam.chernova.finalproject.entity.ext.Client;
import epam.chernova.finalproject.exception.DaoException;

import java.util.List;

public interface IProductDao extends AbstractDao {
    List<Product> findAllProducts() throws DaoException;

}
