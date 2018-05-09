package epam.chernova.finalproject.service.impl;


import epam.chernova.finalproject.entity.Order;
import epam.chernova.finalproject.exception.DaoException;
import epam.chernova.finalproject.exception.ServiceException;
import epam.chernova.finalproject.factory.DaoFactory;
import epam.chernova.finalproject.service.OrderService;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);
    private static DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public Order findActiveOrderByClientId(int idClient) throws ServiceException {
        LOGGER.log(Level.DEBUG, "OrderService: Start findActiveOrderByClientId");
        try {
            LOGGER.log(Level.DEBUG, "OrderService: Finish findActiveOrderByClientId");
            return daoFactory.getOrderDao().findActiveOrderByClientId(idClient);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public void editOrderCost(int idOrder, double deltaTotalCost) throws ServiceException {
        LOGGER.log(Level.DEBUG, "OrderService: Start editOrder");
        try {
            daoFactory.getOrderDao().editOrderCost(idOrder,deltaTotalCost);
            LOGGER.log(Level.DEBUG, "OrderService: Finish editOrder");
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }
}
