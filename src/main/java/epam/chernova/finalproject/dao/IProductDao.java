package epam.chernova.finalproject.dao;

import epam.chernova.finalproject.entity.Product;
import epam.chernova.finalproject.exception.DaoException;

import java.util.List;

public interface IProductDao extends AbstractDao {
    List<Product> findAllProducts() throws DaoException;

    List<Product> findProductByType(String productType) throws DaoException;

    Product findProductById(int idProduct) throws DaoException;

    List<Product> findExistProducts() throws DaoException;

    void deleteProduct(int idProduct) throws DaoException;

    Product findProductByName(String nameEn, String nameRu) throws DaoException;

    void addProduct(String type, String nameEn, String nameRu, double cost, double weight, String imagePath) throws DaoException;

    Product findProductByNameAndId(String nameEn, String nameRu, int idProduct) throws DaoException;

    void editProduct(int idProduct, String type, String nameEn, String nameRu, double cost, double weight, String imagePath) throws DaoException;

}
