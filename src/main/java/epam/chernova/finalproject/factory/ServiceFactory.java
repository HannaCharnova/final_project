package epam.chernova.finalproject.factory;

import epam.chernova.finalproject.service.AdministratorService;
import epam.chernova.finalproject.service.ClientService;
import epam.chernova.finalproject.service.impl.AdministratorServiceImpl;
import epam.chernova.finalproject.service.impl.ClientServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();
    private ClientService clientService = new ClientServiceImpl();
    private AdministratorService administratorService = new AdministratorServiceImpl();

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public AdministratorService getAdministratorService() {
        return administratorService;
    }
}
