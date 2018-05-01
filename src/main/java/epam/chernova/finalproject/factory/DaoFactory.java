package epam.chernova.finalproject.factory;

import epam.chernova.finalproject.dao.ext.AdministratorDao;
import epam.chernova.finalproject.dao.ext.ClientDao;

public class DaoFactory {
    private static final DaoFactory INSTANCE = new DaoFactory();
    private ClientDao clientDao = new ClientDao();
    private AdministratorDao administratorDao = new AdministratorDao();


    public static DaoFactory getInstance(){
        return INSTANCE;
    }

    public ClientDao getClientDao() {
        return clientDao;
    }

    public AdministratorDao getAdministratorDao() {
        return administratorDao;
    }
}
