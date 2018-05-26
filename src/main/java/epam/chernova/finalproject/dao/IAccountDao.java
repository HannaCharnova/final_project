package epam.chernova.finalproject.dao;


import epam.chernova.finalproject.entity.Account;
import epam.chernova.finalproject.entity.Order;
import epam.chernova.finalproject.exception.DaoException;
import epam.chernova.finalproject.exception.ServiceException;

import java.util.List;

public interface IAccountDao extends AbstractDao {
    Account findAccountByClientId(int idClient) throws DaoException;

    void payOrder(int idClient, double totalCost) throws DaoException;

    void payPartOrder(int idClient) throws DaoException;

    void deleteAccount(int idAccount) throws DaoException;

    Account findAccountByNumber(String accountNumber) throws DaoException;

    void addAccount(int idClient, String accountNumber, double credit) throws DaoException;


}
