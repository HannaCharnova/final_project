package epam.chernova.finalproject.service.impl;


import epam.chernova.finalproject.exception.DaoException;
import epam.chernova.finalproject.exception.ServiceException;
import epam.chernova.finalproject.factory.DaoFactory;
import epam.chernova.finalproject.service.OrderProductService;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


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
}
