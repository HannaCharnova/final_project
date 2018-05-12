package epam.chernova.finalproject.service;


import epam.chernova.finalproject.entity.Product;
import epam.chernova.finalproject.exception.ServiceException;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts() throws ServiceException;

    List<Product> findProductByType(String productType) throws ServiceException;

    Product findProductById(int idProduct) throws ServiceException;

    List<Product> findExistProducts() throws ServiceException;

    void deleteProduct(int idProduct) throws ServiceException;

}
