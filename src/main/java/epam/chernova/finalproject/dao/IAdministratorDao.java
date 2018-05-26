package epam.chernova.finalproject.dao;

import epam.chernova.finalproject.entity.User;
import epam.chernova.finalproject.entity.Administrator;
import epam.chernova.finalproject.exception.DaoException;

import java.util.List;

public interface IAdministratorDao extends AbstractDao {
    Administrator signIn(String login, String password) throws DaoException;

    Administrator findAdministratorByLogin(String login) throws DaoException;

    List<Administrator> findAllAdministrators() throws DaoException;

    void deleteAdministrator(int idAdmin) throws DaoException;

    void deleteUser(int idAdmin) throws DaoException;

    User addUser(String login, String password) throws DaoException;

    Administrator addAdministrator(int idUser, String login) throws DaoException;

    User findUserByLogin(String login) throws DaoException;

    Administrator changePassword(int idAdin, String password) throws DaoException;

    Administrator findAdministratorById(int idAdmin) throws DaoException;

    Administrator findAdministratorByIdAndPassword(int idAdmin,String oldPassword) throws DaoException;

}
