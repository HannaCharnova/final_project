package epam.chernova.finalproject.service;

import epam.chernova.finalproject.entity.ext.Client;
import epam.chernova.finalproject.exception.ServiceException;

public interface ClientService {
    Client signIn(String login, String password) throws ServiceException;
    Client signUp(String login, String password,String name,String surname,String email) throws ServiceException;
    Client findClientByLogin(String login);
    boolean checkUniqueEmail(String email);

}
