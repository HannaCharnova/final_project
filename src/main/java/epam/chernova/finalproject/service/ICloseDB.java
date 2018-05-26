package epam.chernova.finalproject.service;

import epam.chernova.finalproject.exception.ServiceException;

public interface ICloseDB {

    void closeConnections() throws ServiceException;
}
