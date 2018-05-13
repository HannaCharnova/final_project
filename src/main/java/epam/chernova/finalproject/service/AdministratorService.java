package epam.chernova.finalproject.service;

import epam.chernova.finalproject.entity.ext.Administrator;
import epam.chernova.finalproject.entity.ext.Client;
import epam.chernova.finalproject.exception.ServiceException;

import java.util.List;

public interface AdministratorService {
    Administrator signIn(String login, String password);

    List<Administrator> findAllAdministrators() throws ServiceException;

    void deleteAdministrator(int idAdmin) throws ServiceException;

    Administrator findAdministratorByLogin(String login) throws ServiceException;

    Administrator addAdministrator(String login,String password) throws ServiceException;

}
