package epam.chernova.finalproject.dao;

import epam.chernova.finalproject.entity.ext.Administrator;
import epam.chernova.finalproject.entity.ext.Client;
import epam.chernova.finalproject.exception.DaoException;
import epam.chernova.finalproject.exception.ServiceException;

import java.util.List;

public interface IAdministratorDao extends AbstractDao {
    Administrator signIn(String login, String password) throws DaoException;

    boolean findAdministratorByLogin(String login) throws DaoException;

    List<Administrator> findAllAdministrators() throws DaoException;

    void deleteAdministrator(int idAdmin) throws DaoException;

    void deleteUser(int idAdmin) throws DaoException;

}
