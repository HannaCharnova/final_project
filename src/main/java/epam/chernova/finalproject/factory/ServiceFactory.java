package epam.chernova.finalproject.factory;

import epam.chernova.finalproject.service.*;
import epam.chernova.finalproject.service.impl.*;

public class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();
    private ClientService clientService = new ClientServiceImpl();
    private AdministratorService administratorService = new AdministratorServiceImpl();
    private ProductService productService = new ProductServiceImpl();
    private OrderService orderService = new OrderServiceImpl();
    private OrderProductService orderProductService = new OrderProductServiceImpl();
    private AccountService accountService = new AccountServiceImpl();


    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public AdministratorService getAdministratorService() {
        return administratorService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public OrderProductService getOrderProductService() {
        return orderProductService;
    }

    public AccountService getAccountService() {
        return accountService;
    }
}
