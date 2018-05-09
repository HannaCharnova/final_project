package epam.chernova.finalproject.service.impl;


import epam.chernova.finalproject.entity.Account;
import epam.chernova.finalproject.exception.DaoException;
import epam.chernova.finalproject.exception.ServiceException;
import epam.chernova.finalproject.factory.DaoFactory;
import epam.chernova.finalproject.service.AccountService;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;


public class AccountServiceImpl implements AccountService {
    private static final Logger LOGGER = LogManager.getLogger(AccountServiceImpl.class);
    private static DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public Account findAccountByClientId(int idClient) throws ServiceException {
        LOGGER.log(Level.DEBUG, "AccountService: Start findAccountByClientId");
        try {
            LOGGER.log(Level.DEBUG, "AccountService: Finish findAccountByClientId");
            return daoFactory.getAccountDao().findAccountByClientId(idClient);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }

    @Override
    public void payOrder(int idClient, double totalCost) throws ServiceException {
        LOGGER.log(Level.DEBUG, "AccountService: Start payOrder");
        try {
            daoFactory.getAccountDao().payOrder(idClient,totalCost);
            LOGGER.log(Level.DEBUG, "AccountService: Finish payOrder");
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }
}
