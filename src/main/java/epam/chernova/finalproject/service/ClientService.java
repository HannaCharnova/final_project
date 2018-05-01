package epam.chernova.finalproject.service;

import epam.chernova.finalproject.entity.ext.Client;

public interface ClientService {
    Client signIn(String login, String password);
    boolean signUp(String login, String password,String name,String surname,String email);
    boolean findClientByLogin(String login);
    boolean checkUniqueEmail(String email);

}
