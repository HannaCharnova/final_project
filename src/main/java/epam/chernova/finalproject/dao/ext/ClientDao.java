package epam.chernova.finalproject.dao.ext;

import epam.chernova.finalproject.connectionpool.ConnectionPool;
import epam.chernova.finalproject.dao.IClientDao;
import epam.chernova.finalproject.entity.User;
import epam.chernova.finalproject.entity.Client;
import epam.chernova.finalproject.exception.ConnectionPoolException;
import epam.chernova.finalproject.exception.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao implements IClientDao {
    private static final Logger LOGGER = Logger.getLogger(ClientDao.class);
    private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM client JOIN user ON client.user_iduser=user.iduser WHERE user.login =? AND user.password = ? AND user.role = 0";
    private static final String FIND_CLIENT_BY_LOGIN = "SELECT * FROM client JOIN user ON client.user_iduser=user.iduser WHERE user.login =?";
    private static final String FIND_CLIENT_BY_ID_AND_PASSWORD = "SELECT * FROM client JOIN user ON client.user_iduser=user.iduser WHERE user.iduser =? AND user.password=?";
    private static final String FIND_USER_BY_LOGIN = "SELECT * FROM user  WHERE user.login =?";
    private static final String FIND_BY_EMAIL = "SELECT * FROM client  JOIN user ON user.iduser=client.user_iduser  WHERE client.email =?";
    private static final String ADD_USER = "INSERT INTO user (login,password,role) VALUES (?,?,?)";
    private static final String ADD_CLIENT = "INSERT INTO client (user_iduser,name,surname,email,address) VALUES (?,?,?,?,?)";
    private static final String FIND_ALL_CLIENTS = "SELECT * FROM client JOIN user ON user.iduser=client.user_iduser";
    private static final String FIND_BY_ID = "SELECT * FROM client JOIN user ON client.user_iduser=user.iduser WHERE user.iduser =?";
    private static final String UNBAN_CLIENT = "UPDATE cafe.client SET cafe.client.ban = 0 WHERE cafe.client.user_iduser = ?";
    private static final String ADD_POINTS = "UPDATE cafe.client SET cafe.client.point = ? WHERE cafe.client.user_iduser = ?";
    private static final String BAN_CLIENT = "UPDATE cafe.client SET cafe.client.ban = 1 WHERE cafe.client.user_iduser = ?";
    private static final String EDIT_CLIENT = "UPDATE cafe.client SET cafe.client.surname = ?,cafe.client.name = ?,cafe.client.email = ?,cafe.client.address=? WHERE cafe.client.user_iduser = ?";
    private static final String CHECK_BAN = "SELECT * FROM client JOIN user ON client.user_iduser=user.iduser WHERE client.user_iduser =? AND client.ban=1";
    private static final String CHANGE_PASSWORD = "UPDATE cafe.user SET cafe.user.password = ? WHERE cafe.user.iduser = ?";
    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;


    @Override
    public Client signIn(String login, String password) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client Dao: start SignIn");
        Client client = null;
        try {

                connectionPool = ConnectionPool.getInstance();
                connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_BY_LOGIN_AND_PASSWORD);
                statement.setString(1, login);
                statement.setString(2, password);
                resultSet = statement.executeQuery();
                if (resultSet.first()) {
                    client = createClientByResultSet(resultSet);
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Client Dao: finish SignIn");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return client;
    }

    @Override
    public Client findClientByLogin(String login) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client Dao: start findClientByLogin");
        Client client = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_CLIENT_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                client = createClientByResultSet(resultSet);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Client Dao: finish findClientByLogin");
            close(resultSet, statement);
            connectionPool.putBackConnection(connection);
        }
        return client;
    }

    @Override
    public Client findClientByEmail(String email) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start findClientByEmail");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_BY_EMAIL);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                return createClientByResultSet(resultSet);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Client DAO: finish findClientByEmail");
            close(resultSet, statement);
            connectionPool.putBackConnection(connection);
        }
        return null;
    }

    @Override
    public User addUser(String login, String password) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start addUser");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(ADD_USER);
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setInt(3, 0);
            if (statement.executeUpdate() != 0) {
                return findUserByLogin(login);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while user executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Client DAO: finish addUser");
            close(resultSet, statement);
            connectionPool.putBackConnection(connection);
        }
        return null;
    }

    @Override
    public Client addClient(int idUser, String login, String name, String surname, String email, String address) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start addClient");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(ADD_CLIENT);
            statement.setInt(1, idUser);
            statement.setString(2, name);
            statement.setString(3, surname);
            statement.setString(4, email);
            statement.setString(5, address);
            if (statement.executeUpdate() != 0) {
                return findClientByLogin(login);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while client executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Client DAO: finish addClient");
            close(resultSet, statement);
            connectionPool.putBackConnection(connection);
        }
        return null;
    }

    @Override
    public User findUserByLogin(String login) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start findUserByLogin");
        User user = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_USER_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                user = new User();
                user.setIdUser(resultSet.getInt("iduser"));
                user.setLogin(resultSet.getString("user.login"));
                user.setPassword(resultSet.getString("user.password"));
                user.setRole(resultSet.getBoolean("user.role"));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Client DAO: finish findUserByLogin");
            close(resultSet, statement);
            connectionPool.putBackConnection(connection);
        }
        return user;
    }

    @Override
    public List<Client> findAllClients() throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start findAllClients");
        List<Client> clients = new ArrayList<>();
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_ALL_CLIENTS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                clients.add(createClientByResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Client DAO: finish findAllClients");
            close(resultSet, statement);
            connectionPool.putBackConnection(connection);
        }
        return clients;
    }

    @Override
    public Client findClientById(int idClient) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start findClientById");
        Client client = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_BY_ID);
            statement.setInt(1, idClient);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                client = createClientByResultSet(resultSet);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Client DAO: finish findClientById");
            close(resultSet, statement);
            connectionPool.putBackConnection(connection);
        }
        return client;

    }

    @Override
    public void unbanClient(int idClient) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start unbanClient");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(UNBAN_CLIENT);
            statement.setInt(1, idClient);
            if (statement.executeUpdate() != 0) {
                return;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Client DAO: finish unbanClient");
            close(resultSet, statement);
            connectionPool.putBackConnection(connection);
        }
    }

    @Override
    public Client addPoints(int idClient, double point) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start addPoints");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(ADD_POINTS);
            statement.setDouble(1, point);
            statement.setDouble(2, idClient);
            if (statement.executeUpdate() != 0) {
                return findClientById(idClient);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Client DAO: finish addPoints");
            close(resultSet, statement);
            connectionPool.putBackConnection(connection);
        }
        return null;
    }

    @Override
    public void banClient(int idClient) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start banClient");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(BAN_CLIENT);
            statement.setInt(1, idClient);
            if (statement.executeUpdate() != 0) {
                return;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Client DAO: finish banClient");
            close(resultSet, statement);
            connectionPool.putBackConnection(connection);
        }
    }

    @Override
    public boolean checkBan(int idClient) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start checkBan");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(CHECK_BAN);
            statement.setInt(1, idClient);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                return true;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Client DAO: finish checkBan");
            close(resultSet, statement);
            connectionPool.putBackConnection(connection);
        }
        return false;

    }

    @Override
    public Client editClient(int idClient, String surname, String name, String email, String address) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start editClient");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(EDIT_CLIENT);
            statement.setString(1, surname);
            statement.setString(2, name);
            statement.setString(3, email);
            statement.setString(4, address);
            statement.setInt(5, idClient);
            if (statement.executeUpdate() != 0) {
                return findClientById(idClient);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Client DAO: finish editClient");
            close(resultSet, statement);
            connectionPool.putBackConnection(connection);
        }
        return null;
    }

    @Override
    public Client changePassword(int idClient, String password) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start changePassword");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(CHANGE_PASSWORD);
            statement.setString(1, password);
            statement.setInt(2, idClient);
            if (statement.executeUpdate() != 0) {
                return findClientById(idClient);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Client DAO: finish changePassword");
            close(resultSet, statement);
            connectionPool.putBackConnection(connection);
        }
        return null;
    }

    @Override
    public Client findClientByIdAndPassword(int idClient, String oldPassword) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client DAO: start findClientByIdAndPassword");
        Client client = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_CLIENT_BY_ID_AND_PASSWORD);
            statement.setInt(1, idClient);
            statement.setString(2, oldPassword);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                client = createClientByResultSet(resultSet);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Client DAO: finish findClientByIdAndPassword");
            close(resultSet, statement);
            connectionPool.putBackConnection(connection);
        }
        return client;
    }


    private Client createClientByResultSet(ResultSet resultSet) throws DaoException {
        Client client = new Client();
        try {
            client.setIdUser(resultSet.getInt("user_iduser"));
            client.setLogin(resultSet.getString("user.login"));
            client.setPassword(resultSet.getString("user.password"));
            client.setName(resultSet.getString("name"));
            client.setSurname(resultSet.getString("surname"));
            client.setEmail(resultSet.getString("email"));
            client.setPoint(resultSet.getDouble("point"));
            client.setBan(resultSet.getBoolean("ban"));
            client.setRole(resultSet.getBoolean("user.role"));
            client.setAddress(resultSet.getString("address"));
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return client;
    }
}
