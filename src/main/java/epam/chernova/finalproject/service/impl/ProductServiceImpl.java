package epam.chernova.finalproject.service.impl;


import epam.chernova.finalproject.entity.Product;
import epam.chernova.finalproject.entity.ext.Administrator;
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


}