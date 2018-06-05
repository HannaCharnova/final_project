package epam.chernova.finalproject.dao.ext;

import epam.chernova.finalproject.connectionpool.ConnectionPool;
import epam.chernova.finalproject.dao.IAdministratorDao;
import epam.chernova.finalproject.entity.User;
import epam.chernova.finalproject.entity.Administrator;
import epam.chernova.finalproject.exception.ConnectionPoolException;
import epam.chernova.finalproject.exception.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDao implements IAdministratorDao {
    private final static Logger LOGGER = LogManager.getLogger(AdministratorDao.class);
    private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM admin JOIN user ON admin.user_iduser=user.iduser WHERE user.login =? AND user.password = ? AND user.role = 1";
    private static final String FIND_ADMIN_BY_LOGIN = "SELECT * FROM admin JOIN user ON admin.user_iduser=user.iduser WHERE user.login =? AND user.role=1";
    private static final String FIND_BY_ID_AND_PASSWORD = "SELECT * FROM admin JOIN user ON admin.user_iduser=user.iduser WHERE user.iduser =? AND user.password=?";
    private static final String FIND_ALL_ADMINS = "SELECT * FROM admin JOIN user ON admin.user_iduser=user.iduser";
    private static final String DELETE_ADMIN = "DELETE FROM admin WHERE admin.user_iduser=?";
    private static final String DELETE_USER = "DELETE FROM user WHERE user.iduser=?";
    private static final String FIND_USER_BY_LOGIN = "SELECT * FROM user  WHERE user.login =?";
    private static final String ADD_USER = "INSERT INTO cafe.user (login,password,role) VALUES (?,?,?)";
    private static final String ADD_ADMIN = "INSERT INTO cafe.admin (user_iduser,is_main) VALUES (?,?)";
    private static final String CHANGE_PASSWORD = "UPDATE cafe.user SET cafe.user.password = ? WHERE cafe.user.iduser = ?";
    private static final String FIND_BY_ID = "SELECT * FROM admin JOIN user ON admin.user_iduser=user.iduser WHERE user.iduser =?";
    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;


    @Override
    public Administrator signIn(String login, String password) throws DaoException {
        LOGGER.log(Level.DEBUG, "Administrator Dao: start signIn");
        Administrator administrator = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_BY_LOGIN_AND_PASSWORD);
                statement.setString(1, login);
                statement.setString(2, password);
                resultSet = statement.executeQuery();
                if (resultSet.first()) {
                    administrator = createAdminByResultSet(resultSet);
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Administrator Dao: finish signIn");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        }
        return administrator;
    }

    @Override
    public Administrator findAdministratorByLogin(String login) throws DaoException {
        LOGGER.log(Level.DEBUG, "AdministratorDao: start findAdministratorByLogin");
        Administrator administrator = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_ADMIN_BY_LOGIN);
                statement.setString(1, login);
                resultSet = statement.executeQuery();
                if (resultSet.first()) {
                    administrator = createAdminByResultSet(resultSet);
                }
            } finally {
                LOGGER.log(Level.DEBUG, "AdministratorDao: finish findAdministratorByLogin");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return administrator;
    }

    @Override
    public List<Administrator> findAllAdministrators() throws DaoException {
        LOGGER.log(Level.DEBUG, "Administrator DAO: start findAllAdministrators");
        List<Administrator> administrators = new ArrayList<>();
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_ALL_ADMINS);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    administrators.add(createAdminByResultSet(resultSet));
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Administrator DAO: finish findAllAdministrators");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return administrators;

    }

    @Override
    public void deleteAdministrator(int idAdmin) throws DaoException {
        LOGGER.log(Level.DEBUG, "Administrator DAO: start deleteAdministrator");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(DELETE_ADMIN);
                statement.setInt(1, idAdmin);
                if (statement.executeUpdate() != 0) {
                    return;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Administrator DAO: finish deleteAdministrator");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
    }


    @Override
    public void deleteUser(int idAdmin) throws DaoException {
        LOGGER.log(Level.DEBUG, "Administrator DAO: start deleteUser");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(DELETE_USER);
                statement.setInt(1, idAdmin);
                if (statement.executeUpdate() != 0) {
                    return;
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Administrator DAO: finish deleteUser");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }

    }

    @Override
    public User addUser(String login, String password) throws DaoException {
        LOGGER.log(Level.DEBUG, "Administrator DAO: start addUser");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(ADD_USER);
                statement.setString(1, login);
                statement.setString(2, password);
                statement.setInt(3, 1);
                if (statement.executeUpdate() != 0) {
                    return findUserByLogin(login);
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Administrator DAO: finish addUser");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return null;
    }

    @Override
    public User findUserByLogin(String login) throws DaoException {
        LOGGER.log(Level.DEBUG, "Administrator DAO: start findUserByLogin");
        User user = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_USER_BY_LOGIN);
                statement.setString(1, login);
                resultSet = statement.executeQuery();
                if (resultSet.first()) {
                    user = new User();
                    user.setIdUser(resultSet.getInt("iduser"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(resultSet.getBoolean("role"));
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Administrator DAO: finish findUserByLogin");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return user;
    }

    @Override
    public Administrator changePassword(int idAdmin, String password) throws DaoException {
        LOGGER.log(Level.DEBUG, "Administrator DAO: start changePassword");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(CHANGE_PASSWORD);
                statement.setString(1, password);
                statement.setInt(2, idAdmin);
                if (statement.executeUpdate() != 0) {
                    return findAdministratorById(idAdmin);
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Administrator DAO: finish changePassword");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return null;

    }

    @Override
    public Administrator findAdministratorById(int idAdmin) throws DaoException {
        LOGGER.log(Level.DEBUG, "Administrator DAO: start findAdministratorById");
        Administrator administrator = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_BY_ID);
                statement.setInt(1, idAdmin);
                resultSet = statement.executeQuery();
                if (resultSet.first()) {
                    administrator = createAdminByResultSet(resultSet);
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Administrator DAO: finish findAdministratorById");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return administrator;

    }

    @Override
    public Administrator findAdministratorByIdAndPassword(int idAdmin, String oldPassword) throws DaoException {
        LOGGER.log(Level.DEBUG, "Administrator DAO: start findAdministratorByIdAndPassword");
        Administrator administrator = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(FIND_BY_ID_AND_PASSWORD);
                statement.setInt(1, idAdmin);
                statement.setString(2, oldPassword);
                resultSet = statement.executeQuery();
                if (resultSet.first()) {
                    administrator = createAdminByResultSet(resultSet);
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Administrator DAO: finish findAdministratorByIdAndPassword");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return administrator;
    }


    @Override
    public Administrator addAdministrator(int idUser, String login) throws DaoException {
        LOGGER.log(Level.DEBUG, "Administrator DAO: start addAdministrator");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            try {
                statement = connection.prepareStatement(ADD_ADMIN);
                statement.setInt(1, idUser);
                statement.setInt(2, 0);
                if (statement.executeUpdate() != 0) {
                    return findAdministratorByLogin(login);
                }
            } finally {
                LOGGER.log(Level.DEBUG, "Administrator DAO: finish addAdministrator");
                close(resultSet, statement);
                connectionPool.putBackConnection(connection);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception while client executing SQL query", e);
        }
        return null;

    }


    private Administrator createAdminByResultSet(ResultSet resultSet) throws DaoException {
        Administrator administrator = new Administrator();
        try {
            administrator.setIdUser(resultSet.getInt("user_iduser"));
            administrator.setLogin(resultSet.getString("user.login"));
            administrator.setPassword(resultSet.getString("user.password"));
            administrator.setMain(resultSet.getBoolean("is_main"));
            administrator.setRole(resultSet.getBoolean("user.role"));
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return administrator;
    }
}
