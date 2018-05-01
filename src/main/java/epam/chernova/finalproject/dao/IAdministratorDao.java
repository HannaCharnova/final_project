package epam.chernova.finalproject.dao;

import epam.chernova.finalproject.entity.ext.Administrator;
import epam.chernova.finalproject.entity.ext.Client;
import epam.chernova.finalproject.exception.DaoException;

public interface IAdministratorDao extends AbstractDao{
    Administrator signIn(String login, String password) throws DaoException;

}
