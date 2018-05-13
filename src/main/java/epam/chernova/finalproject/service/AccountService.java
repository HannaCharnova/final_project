package epam.chernova.finalproject.service;


import epam.chernova.finalproject.entity.Account;
import epam.chernova.finalproject.exception.ServiceException;

public interface AccountService {
    Account findAccountByClientId(int idClient) throws ServiceException;

    void payOrder(int idClient,double totalCost) throws ServiceException;
}
