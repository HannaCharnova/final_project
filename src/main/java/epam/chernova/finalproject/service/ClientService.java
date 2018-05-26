package epam.chernova.finalproject.service;

import epam.chernova.finalproject.entity.Client;
import epam.chernova.finalproject.exception.ServiceException;

import java.util.List;

public interface ClientService {
    Client signIn(String login, String password) throws ServiceException;

    Client signUp(String login, String password, String name, String surname, String email,String address) throws ServiceException;

    Client findClientByLogin(String login) throws ServiceException;

    Client findClientByEmail(String email) throws ServiceException;

    List<Client> findAllClients() throws ServiceException;

    Client findClientById(int idClient) throws ServiceException;

    void unbanClient(int idClient) throws ServiceException;

    void banClient(int idClient) throws ServiceException;

    boolean checkBan(int idClient) throws ServiceException;

    Client editClient(int idClient, String surname, String name, String email,String address) throws ServiceException;

    Client changePassword(int idClient, String password) throws ServiceException;

    Client findClientByIdAndPassword(int idClient,String oldPassword) throws ServiceException;


}
