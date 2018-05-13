package epam.chernova.finalproject.service;

import epam.chernova.finalproject.entity.ext.Client;
import epam.chernova.finalproject.exception.ServiceException;

import java.util.List;

public interface ClientService {
    Client signIn(String login, String password) throws ServiceException;

    Client signUp(String login, String password, String name, String surname, String email) throws ServiceException;

    Client findClientByLogin(String login);

    Client findClientByEmail(String email);

    List<Client> findAllClients() throws ServiceException;

    Client findClientById(int idClient) throws ServiceException;
}
