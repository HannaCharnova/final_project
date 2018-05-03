package epam.chernova.finalproject.connectionpool;

import epam.chernova.finalproject.webenum.DBparameter;
import org.apache.log4j.Level;

import java.sql.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static ConnectionPool instance;
    private static AtomicBoolean instanceCreated = new AtomicBoolean();
    private static Lock instanceLock = new ReentrantLock();
    private static Lock connectionLock = new ReentrantLock();

    private BlockingQueue<Connection> connections;
    private BlockingQueue<Connection> givenConnections;
    private int waitingTime;


    private ConnectionPool() {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        String driver = dbResourceManager.getValue(DBparameter.DRIVER.getValue());
        String user = dbResourceManager.getValue(DBparameter.USER.getValue());
        String url = dbResourceManager.getValue(DBparameter.URL.getValue());
        String password = dbResourceManager.getValue(DBparameter.PASSWORD.getValue());
        try {
            int poolSize = Integer.parseInt(dbResourceManager.getValue(DBparameter.POOL_SIZE.getValue()));
            waitingTime = Integer.parseInt(dbResourceManager.getValue(DBparameter.WAITING_TIME.getValue()));
            Class.forName(driver);
            connections = new ArrayBlockingQueue<>(poolSize);
            givenConnections = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                connections.add(DriverManager.getConnection(url, user, password));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
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
            if (connectionLock.tryLock(waitingTime, TimeUnit.SECONDS)) {
                connection = connections.poll();
                givenConnections.add(connection);
                return connection;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            connectionLock.unlock();
        }
        return null;
    }

    public void putBack(Connection connection, ResultSet resultSet, Statement statement) {
        if (connection != null) {
            connections.add(connection);
            givenConnections.remove(connection);
        }

    }

    public void releasePool() {
        while (!givenConnections.isEmpty()) {
            try {
                Connection connection = givenConnections.take();
                connection.close();
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
            }
        }
        while (!connections.isEmpty()) {
            try {
                Connection connection = connections.take();
                connection.close();
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


