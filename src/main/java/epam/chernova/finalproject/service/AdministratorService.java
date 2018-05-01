package epam.chernova.finalproject.service;

import epam.chernova.finalproject.entity.ext.Administrator;
import epam.chernova.finalproject.entity.ext.Client;

public interface AdministratorService {
    Administrator signIn(String login, String password);
}
