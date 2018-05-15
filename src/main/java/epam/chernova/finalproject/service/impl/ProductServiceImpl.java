package epam.chernova.finalproject.service.impl;


import epam.chernova.finalproject.entity.Product;
import epam.chernova.finalproject.entity.ext.Administrator;
import epam.chernova.finalproject.entity.ext.Client;
import epam.chernova.finalproject.exception.DaoException;
import epam.chernova.finalproject.exception.ServiceException;
import epam.chernova.finalproject.factory.DaoFactory;
import epam.chernova.finalproject.service.AdministratorService;
import epam.chernova.finalproject.service.ProductService;
import epam.chernova.finalproject.util.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;


public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LogManager.getLogger(ProductServiceImpl.class);
    private static DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public List<Product> findAllProducts() throws ServiceException {
        LOGGER.log(Level.DEBUG, "ProductService: Start get all products");
        try {
            LOGGER.log(Level.DEBUG, "Product Service: Finish get all products");
            return daoFactory.getProductDao().findAllProducts();
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public List<Product> findExistProducts() throws ServiceException {
        LOGGER.log(Level.DEBUG, "ProductService: Start get exist products");
        try {
            LOGGER.log(Level.DEBUG, "Product Service: Finish get exist products");
            return daoFactory.getProductDao().findExistProducts();
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public void deleteProduct(int idProduct) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ProductService: Start deleteProduct");
        try {
            LOGGER.log(Level.DEBUG, "Product Service: Finish deleteProduct");
            daoFactory.getProductDao().deleteProduct(idProduct);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public Product findProductByName(String nameEn, String nameRu) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ProductService: Start find product by name");
        try {
            LOGGER.log(Level.DEBUG, "Product Service: Finish find product by name");
            return daoFactory.getProductDao().findProductByName(nameEn, nameRu);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public void addProduct(String type, String nameEn, String nameRu, double cost, double weight, String imagePath) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Product Service: start addProduct");
        try {
            if (Validator.isNull(type, nameEn, nameRu, cost, weight, imagePath) && Validator.isEmptyString(type, nameEn, nameRu, imagePath) && Validator.matchProductName(nameEn,nameRu)) {
                daoFactory.getProductDao().addProduct(type, nameEn, nameRu, cost, weight, imagePath);
                LOGGER.log(Level.DEBUG, "Product Service: end addProduct");
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public Product findProductByNameAndId(String nameEn, String nameRu, int idProduct) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ProductService: Start findProductByNameAndId");
        try {
            LOGGER.log(Level.DEBUG, "Product Service: Finish findProductByNameAndId");
            return daoFactory.getProductDao().findProductByNameAndId(nameEn, nameRu,idProduct);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public void editProduct(int idProduct, String type, String nameEn, String nameRu, double cost, double weight, String imagePath) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Product Service: start editProduct");
        try {
            if (Validator.isNull(type, nameEn, nameRu, cost, weight, imagePath) && Validator.isEmptyString(type, nameEn, nameRu, imagePath) && Validator.matchProductName(nameEn,nameRu)) {
                daoFactory.getProductDao().editProduct(idProduct,type, nameEn, nameRu, cost, weight, imagePath);
                LOGGER.log(Level.DEBUG, "Product Service: end editProduct");
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }


    @Override
    public List<Product> findProductByType(String productType) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ProductService: Start find product by type");
        try {
            if (Validator.isNull(productType) && Validator.isEmptyString(productType)) {
                LOGGER.log(Level.DEBUG, "Product Service: Finish find product by type");
                return daoFactory.getProductDao().findProductByType(productType);
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
        return null;
    }

    @Override
    public Product findProductById(int idProduct) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ProductService: Start find product by id");
        try {
            LOGGER.log(Level.DEBUG, "Product Service: Finish find product by id");
            return daoFactory.getProductDao().findProductById(idProduct);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }
}
