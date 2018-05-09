package epam.chernova.finalproject.factory;

import epam.chernova.finalproject.dao.ext.*;
import epam.chernova.finalproject.entity.Product;

public class DaoFactory {
    private static final DaoFactory INSTANCE = new DaoFactory();
    private ClientDao clientDao = new ClientDao();
    private AdministratorDao administratorDao = new AdministratorDao();
    private ProductDao productDao = new ProductDao();
    private OrderDao orderDao = new OrderDao();
    private OrderProductDao orderProductDao = new OrderProductDao();
    private AccountDao accountDao = new AccountDao();


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

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public OrderProductDao getOrderProductDao() {
        return orderProductDao;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }
}
