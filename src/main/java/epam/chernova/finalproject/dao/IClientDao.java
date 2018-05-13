package epam.chernova.finalproject.dao;

import epam.chernova.finalproject.entity.User;
import epam.chernova.finalproject.entity.ext.Client;
import epam.chernova.finalproject.exception.DaoException;
import epam.chernova.finalproject.exception.ServiceException;

import java.sql.ResultSet;
import java.util.List;

public interface IClientDao extends AbstractDao {
    Client signIn(String login, String password) throws DaoException;

    Client findClientByLogin(String login) throws DaoException;

    Client findClientByEmail(String email) throws DaoException;

    User addUser(String login, String password) throws DaoException;

    Client addClient(int idUser,String login,String name, String surname, String email) throws DaoException;

    User findUserByLogin(String login) throws DaoException;

    List<Client> findAllClients() throws DaoException;

    Client findClientById(int idClient) throws DaoException;
}
