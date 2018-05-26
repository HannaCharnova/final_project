package epam.chernova.finalproject.service.impl;

import epam.chernova.finalproject.connectionpool.ConnectionPool;
import epam.chernova.finalproject.connectionpool.ICloseConnectionPool;
import epam.chernova.finalproject.exception.ConnectionPoolException;
import epam.chernova.finalproject.exception.ServiceException;
import epam.chernova.finalproject.service.ICloseDB;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class CloseDB implements ICloseDB {
    private static Logger LOGGER = Logger.getLogger(CloseDB.class);

    public void closeConnections() throws ServiceException {
        LOGGER.log(Level.DEBUG, "Service: Close Connection");
        try {
            ICloseConnectionPool pool =  ConnectionPool.getInstance();
            pool.releasePool();
        } catch (ConnectionPoolException e) {
            throw new ServiceException(e.getMessage());
        }
        LOGGER.log(Level.DEBUG, "Service: Close Connection - success");
    }
}
