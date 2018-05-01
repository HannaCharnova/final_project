package epam.chernova.finalproject.dao;

import epam.chernova.finalproject.entity.ext.Client;
import epam.chernova.finalproject.exception.DaoException;

public interface IClientDao extends AbstractDao{
    Client signIn(String login, String password) throws DaoException;
    boolean findClientByLogin(String login) throws DaoException;
    boolean findClientByEmail(String email) throws DaoException;
    boolean addClient(String login,String password,String name,String surname,String email) throws DaoException;
}
