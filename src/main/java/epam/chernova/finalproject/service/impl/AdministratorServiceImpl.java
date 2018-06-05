package epam.chernova.finalproject.service.impl;


import epam.chernova.finalproject.entity.User;
import epam.chernova.finalproject.entity.Administrator;
import epam.chernova.finalproject.exception.DaoException;
import epam.chernova.finalproject.exception.ServiceException;
import epam.chernova.finalproject.factory.DaoFactory;
import epam.chernova.finalproject.service.AdministratorService;
import epam.chernova.finalproject.util.Hasher;
import epam.chernova.finalproject.util.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;


public class AdministratorServiceImpl implements AdministratorService {
    private static final Logger LOGGER = LogManager.getLogger(AdministratorServiceImpl.class);
    private static DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public Administrator signIn(String login, String password) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Administrator Service: start SignInCommand");
        Administrator administrator = null;
        try {
            if (Validator.isNull(login, password) && Validator.isEmptyString(login, password) && Validator.matchLogin(login) && Validator.matchPassword(password)) {
                administrator = daoFactory.getAdministratorDao().signIn(login, password);
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
        LOGGER.log(Level.DEBUG, "Administrator Service: end SignInCommand");
        return administrator;
    }

    @Override
    public List<Administrator> findAllAdministrators() throws ServiceException {
        LOGGER.log(Level.DEBUG, "AdministratorService: Start findAllAdministrators");
        try {
            LOGGER.log(Level.DEBUG, "AdministratorService: Finish findAllAdministrators");
            return daoFactory.getAdministratorDao().findAllAdministrators();
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }

    @Override
    public void deleteAdministrator(int idAdmin) throws ServiceException {
        LOGGER.log(Level.DEBUG, "AdministratorService: Start deleteAdministrator");
        try {
            daoFactory.getAdministratorDao().deleteAdministrator(idAdmin);
            daoFactory.getAdministratorDao().deleteUser(idAdmin);
            LOGGER.log(Level.DEBUG, "deleteAdministrator: Finish deleteAdministrator");
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }

    }

    @Override
    public Administrator findAdministratorByLogin(String login) throws ServiceException {
        LOGGER.log(Level.DEBUG, "AdministratorService: Start findAdministratorByLogin");
        try {
            if (Validator.isNull(login) && Validator.isEmptyString(login) && Validator.matchLogin(login)) {
                LOGGER.log(Level.DEBUG, "AdministratorService: Finish findAdministratorByLogin");
                return daoFactory.getAdministratorDao().findAdministratorByLogin(login);
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
        return null;
    }

    @Override
    public Administrator addAdministrator(String login, String password) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Administrator Service: start addAdministrator");
        Administrator administrator = null;
        User user = null;
        try {
            if (Validator.isNull(login, password) && Validator.isEmptyString(login, password) && Validator.matchLogin(login) && Validator.matchPassword(password)) {
                user = daoFactory.getAdministratorDao().addUser(login, password);
                if (user != null) {
                    administrator = daoFactory.getAdministratorDao().addAdministrator(user.getIdUser(), login);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
        LOGGER.log(Level.DEBUG, "AdministratorService: end addAdministrator");
        return administrator;

    }

    @Override
    public Administrator changePassword(int idAdmin, String password) throws ServiceException {
        LOGGER.log(Level.DEBUG, "AdministratorService: Start changePassword");
        try {
            if (Validator.isNull(password) && Validator.isEmptyString(password) && Validator.matchPassword(password)) {
                LOGGER.log(Level.DEBUG, "AdministratorService: Finish changePassword");
                return daoFactory.getAdministratorDao().changePassword(idAdmin, password);
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
        return null;
    }

    @Override
    public Administrator findAdministratorByIdAndPassword(int idAdmin, String oldPassword) throws ServiceException {
        LOGGER.log(Level.DEBUG, "AdministratorService: Start findAdministratorByIdAndPassword");
        try {
            if(Validator.isEmptyString(oldPassword)&&Validator.isNull(oldPassword)&&Validator.matchPassword(oldPassword)) {
                LOGGER.log(Level.DEBUG, "AdministratorService: Finish findAdministratorByIdAndPassword");
                return daoFactory.getAdministratorDao().findAdministratorByIdAndPassword(idAdmin, oldPassword);
            }
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
        return null;
    }
}
