package epam.chernova.finalproject.service.impl;


import epam.chernova.finalproject.entity.OrderProduct;
import epam.chernova.finalproject.exception.DaoException;
import epam.chernova.finalproject.exception.ServiceException;
import epam.chernova.finalproject.factory.DaoFactory;
import epam.chernova.finalproject.service.OrderProductService;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;


public class OrderProductServiceImpl implements OrderProductService {
    private static final Logger LOGGER = LogManager.getLogger(OrderProductServiceImpl.class);
    private static DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public void addOrderProduct(int idOrder, int idProduct, int quantity) throws ServiceException {
        LOGGER.log(Level.DEBUG, "OrderProductService: Start addOrderProduct");
        try {
            daoFactory.getOrderProductDao().addOrderProduct(idOrder,idProduct,quantity);
            LOGGER.log(Level.DEBUG, "OrderProductService: Finish addOrderProduct");
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }

    @Override
    public void removeOrderProduct(int idOrder, int idProduct) throws ServiceException {
        LOGGER.log(Level.DEBUG, "OrderProductService: Start removeOrderProduct");
        try {
            daoFactory.getOrderProductDao().removeOrderProduct(idOrder,idProduct);
            LOGGER.log(Level.DEBUG, "OrderProductService: Finish removeOrderProduct");
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public List<OrderProduct> findOrderProductsByClientId(int idClient) throws ServiceException {
        LOGGER.log(Level.DEBUG, "OrderProductService: Start findOrderProductsByClientId");
        try {
            LOGGER.log(Level.DEBUG, "OrderProductService: Finish findOrderProductsByClientId");
            return daoFactory.getOrderProductDao().findOrderProductsByClientId(idClient);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }
}
