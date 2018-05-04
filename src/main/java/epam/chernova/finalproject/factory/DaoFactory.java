package epam.chernova.finalproject.factory;

import epam.chernova.finalproject.dao.ext.AdministratorDao;
import epam.chernova.finalproject.dao.ext.ClientDao;
import epam.chernova.finalproject.dao.ext.ProductDao;
import epam.chernova.finalproject.entity.Product;

public class DaoFactory {
    private static final DaoFactory INSTANCE = new DaoFactory();
    private ClientDao clientDao = new ClientDao();
    private AdministratorDao administratorDao = new AdministratorDao();
    private ProductDao productDao = new ProductDao();


    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    public ClientDao getClientDao() {
        return clientDao;
    }

    public AdministratorDao getAdministratorDao() {
        return administratorDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }
}
