package epam.chernova.finalproject.connectionpool;

import epam.chernova.finalproject.exception.ConnectionPoolException;
import epam.chernova.finalproject.webenum.DBparameter;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.MissingResourceException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool implements ICloseConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
    private static ConnectionPool instance;
    private static AtomicBoolean instanceCreated = new AtomicBoolean();
    private static Lock instanceLock = new ReentrantLock();

    private BlockingQueue<Connection> connections;
    private BlockingQueue<Connection> givenConnections;


    private ConnectionPool() throws ConnectionPoolException {
        try {
            DBResourceManager dbResourceManager = DBResourceManager.getInstance();
            String driver = dbResourceManager.getValue(DBparameter.DRIVER.getValue());
            String user = dbResourceManager.getValue(DBparameter.USER.getValue());
            String url = dbResourceManager.getValue(DBparameter.URL.getValue());
            String password = dbResourceManager.getValue(DBparameter.PASSWORD.getValue());
            int poolSize = Integer.parseInt(dbResourceManager.getValue(DBparameter.POOL_SIZE.getValue()));
            Class.forName(driver);
            connections = new ArrayBlockingQueue<>(poolSize);
            givenConnections = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                connections.add(DriverManager.getConnection(url, user, password));
            }
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.FATAL, "Driver load exception: " + e);
            throw new ConnectionPoolException("Driver load exception: " + e);
        } catch (MissingResourceException e) {
            LOGGER.log(Level.FATAL, "Error of upload config: " + e);
            throw new ConnectionPoolException("Error of upload config: " + e);
        } catch (SQLException e) {
            throw new ConnectionPoolException("Error of upload config: " + e);
        }
    }

    public static ConnectionPool getInstance() throws ConnectionPoolException {
        if (!instanceCreated.get()) {
            try {
                instanceLock.lock();
                if (instance == null && !instanceCreated.get()) {
                    instance = new ConnectionPool();
                    instanceCreated.set(true);
                }
            } finally {
                instanceLock.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection;
        try {
            connection = connections.take();
            givenConnections.add(connection);
            return connection;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }

    public void putBackConnection(Connection connection) {
        if (connection != null) {
            connections.add(connection);
            givenConnections.remove(connection);
        }

    }

    public void releasePool() {
        while (!connections.isEmpty()) {
            try {
                Connection connection = connections.take();
                connection.close();
            } catch (InterruptedException | SQLException e) {
                LOGGER.log(Level.ERROR, "Can't close connection" + e);
            }
        }
    }
}


