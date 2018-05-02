package epam.chernova.finalproject.dao;

import epam.chernova.finalproject.entity.ext.Client;
import epam.chernova.finalproject.exception.DaoException;

public interface IClientDao extends AbstractDao {
    Client signIn(String login, String password) throws DaoException;

    Client findClientByLogin(String login) throws DaoException;

    boolean findClientByEmail(String email) throws DaoException;

    boolean addUser(String login, String password) throws DaoException;

    Client addClient(String login,String name, String surname, String email) throws DaoException;

}
