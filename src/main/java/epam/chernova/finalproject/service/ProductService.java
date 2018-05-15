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

    Product findProductByName(String nameEn, String nameRu) throws ServiceException;

    void addProduct(String type, String nameEn, String nameRu, double cost, double weight, String imagePath) throws ServiceException;

    Product findProductByNameAndId(String nameEn, String nameRu, int idProduct) throws ServiceException;

    void editProduct(int idProduct, String type, String nameEn, String nameRu, double cost, double weight, String imagePath) throws ServiceException;
}
