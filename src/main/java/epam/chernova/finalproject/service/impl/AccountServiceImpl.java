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
    public void payOrder(int idClient, double totalCost,double point) throws ServiceException {
        LOGGER.log(Level.DEBUG, "AccountService: Start payOrder");
        try {
            daoFactory.getAccountDao().payOrder(idClient,totalCost-point);
            LOGGER.log(Level.DEBUG, "AccountService: Finish payOrder");
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }

    @Override
    public void payPartOrder(int idClient) throws ServiceException {
        LOGGER.log(Level.DEBUG, "AccountService: Start payPartOrder");
        try {
            daoFactory.getAccountDao().payPartOrder(idClient);
            LOGGER.log(Level.DEBUG, "AccountService: Finish payPartOrder");
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }

    @Override
    public void deleteAccount(int idAccount) throws ServiceException {
        LOGGER.log(Level.DEBUG, "AccountService: Start deleteAccount");
        try {
            daoFactory.getAccountDao().deleteAccount(idAccount);
            LOGGER.log(Level.DEBUG, "AccountService: Finish deleteAccount");
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }

    @Override
    public Account findAccountByNumber(String accountNumber) throws ServiceException {
        LOGGER.log(Level.DEBUG, "AccountService: Start findAccountByNumber");
        try {
            LOGGER.log(Level.DEBUG, "AccountService: Finish findAccountByNumber");
            return daoFactory.getAccountDao().findAccountByNumber(accountNumber);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public void addAccount(int idClient, String accountNumber) throws ServiceException {
        LOGGER.log(Level.DEBUG, "AccountService: Start addAccount");
        try {
            double credit= Math.random()*1000;
            System.out.println(credit);
            daoFactory.getAccountDao().addAccount(idClient,accountNumber,credit);
            LOGGER.log(Level.DEBUG, "AccountService: Finish addAccount");
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }
}
