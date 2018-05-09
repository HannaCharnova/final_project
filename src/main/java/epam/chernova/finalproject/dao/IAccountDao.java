package epam.chernova.finalproject.dao;


import epam.chernova.finalproject.entity.Account;
import epam.chernova.finalproject.entity.Order;
import epam.chernova.finalproject.exception.DaoException;

import java.util.List;

public interface IAccountDao extends AbstractDao {
    Account findAccountByClientId(int idClient) throws DaoException;

    void payOrder(int idClient,double totalCost) throws DaoException;
}
