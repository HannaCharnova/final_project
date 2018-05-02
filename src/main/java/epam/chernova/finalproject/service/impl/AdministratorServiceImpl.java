package epam.chernova.finalproject.service.impl;


import epam.chernova.finalproject.dao.ext.ClientDao;
import epam.chernova.finalproject.entity.ext.Administrator;
import epam.chernova.finalproject.entity.ext.Client;
import epam.chernova.finalproject.exception.DaoException;
import epam.chernova.finalproject.exception.ValidatorException;
import epam.chernova.finalproject.factory.DaoFactory;
import epam.chernova.finalproject.service.AdministratorService;
import epam.chernova.finalproject.service.ClientService;
import epam.chernova.finalproject.util.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class AdministratorServiceImpl implements AdministratorService {
    private static final Logger LOGGER = LogManager.getLogger(AdministratorServiceImpl.class);
    private static DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public Administrator signIn(String login, String password) {
        LOGGER.log(Level.DEBUG, "Administrator Service: start SignIn");
        try {
            Validator.isNull(login, password);
            Validator.isEmptyString(login, password);
            Validator.matchLogin(login);
            Validator.matchPassword(password);
            LOGGER.log(Level.DEBUG, "Administrator Service: end SignIn");
            return daoFactory.getAdministratorDao().signIn(login, password);
        } catch (DaoException  e) {
            return null;
        }
    }


}
