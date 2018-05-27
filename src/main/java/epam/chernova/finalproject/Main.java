package epam.chernova.finalproject;

import epam.chernova.finalproject.dao.ext.ClientDao;
import epam.chernova.finalproject.entity.Client;
import epam.chernova.finalproject.exception.DaoException;
import epam.chernova.finalproject.util.Hasher;

import java.util.List;

public class Main {
    public static void main(String[] args) throws DaoException {
        System.out.println(Hasher.sha1Hash("client"));
    }
}
