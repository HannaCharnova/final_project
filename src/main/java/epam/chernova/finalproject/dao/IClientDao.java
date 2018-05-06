package epam.chernova.finalproject.dao;

import epam.chernova.finalproject.entity.User;
import epam.chernova.finalproject.entity.ext.Client;
import epam.chernova.finalproject.exception.DaoException;

import java.sql.ResultSet;

public interface IClientDao extends AbstractDao {
    Client signIn(String login, String password) throws DaoException;

    Client findClientByLogin(String login) throws DaoException;

    Client findClientByEmail(String email) throws DaoException;

    User addUser(String login, String password) throws DaoException;

    Client addClient(int idUser,String login,String name, String surname, String email) throws DaoException;

    User findUserByLogin(String login) throws DaoException;

}
