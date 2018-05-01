package epam.chernova.finalproject.service.impl;


import epam.chernova.finalproject.dao.ext.ClientDao;
import epam.chernova.finalproject.entity.ext.Client;
import epam.chernova.finalproject.exception.DaoException;
import epam.chernova.finalproject.exception.ValidatorException;
import epam.chernova.finalproject.factory.DaoFactory;
import epam.chernova.finalproject.service.ClientService;
import epam.chernova.finalproject.util.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class ClientServiceImpl implements ClientService {
    private static final Logger LOGGER = LogManager.getLogger(ClientServiceImpl.class);
    private static DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public Client signIn(String login, String password) {
        LOGGER.log(Level.DEBUG, "Client Service: start SignIn");
        try {
            Validator.isNull(login, password);
            Validator.isEmptyString(login, password);
            Validator.matchLogin(login);
            Validator.matchPassword(password);
            LOGGER.log(Level.DEBUG, "Client Service: end SignIn");
            return daoFactory.getClientDao().signIn(login, password);
        } catch (DaoException |ValidatorException e) {
            return null;
        }
    }

    @Override
    public boolean signUp(String login, String password, String name, String surname, String email) {
        ClientDao clientDao = daoFactory.getClientDao();
        boolean result = false;
        try {
            result = clientDao.addClient(login, password, name, surname, email);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "");
        }
        return result;
    }

    @Override
    public boolean findClientByLogin(String login) {
        ClientDao clientDao = daoFactory.getClientDao();
        boolean result = false;
        try {
            result = clientDao.findClientByLogin(login);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "");
        }
        return result;
    }

    @Override
    public boolean checkUniqueEmail(String email) {
        ClientDao clientDao = daoFactory.getClientDao();
        boolean result = false;
        try {
            result = clientDao.findClientByEmail(email);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "");
        }
        return result;
    }


}
