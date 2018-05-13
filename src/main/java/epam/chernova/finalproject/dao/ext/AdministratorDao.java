package epam.chernova.finalproject.dao.ext;

import epam.chernova.finalproject.connectionpool.ConnectionPool;
import epam.chernova.finalproject.dao.IAdministratorDao;
import epam.chernova.finalproject.entity.ext.Administrator;
import epam.chernova.finalproject.entity.ext.Client;
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
    private static final String FIND_ALL_ADMINS = "SELECT * FROM admin JOIN user ON admin.user_iduser=user.iduser";
    private static final String DELETE_ADMIN = "DELETE FROM admin WHERE admin.user_iduser=?";
    private static final String DELETE_USER = "DELETE FROM user WHERE user.iduser=?";
    private ConnectionPool connectionPool = ConnectionPool.getInstance();


    @Override
    public Administrator signIn(String login, String password) throws DaoException {
        LOGGER.log(Level.DEBUG, "Client Dao: start SignIn");
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Administrator administrator = null;
        try {
            statement = connection.prepareStatement(FIND_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                administrator = new Administrator();
                administrator.setIdUser(resultSet.getInt("user_iduser"));
                administrator.setLogin(resultSet.getString("user.login"));
                administrator.setPassword(resultSet.getString("user.password"));
                administrator.setMain(resultSet.getBoolean("is_main"));
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            connectionPool.putBack(connection, resultSet, statement);
        }
        LOGGER.log(Level.DEBUG, "Administrator Dao: finish SignIn");
        return administrator;
    }

    @Override
    public boolean findAdministratorByLogin(String login) throws DaoException {
        LOGGER.log(Level.DEBUG, "AdministratorDao: start findAdministratorByLogin");
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_ADMIN_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "AdministratorDao: finish findAdministratorByLogin");
            connectionPool.putBack(connection, resultSet, statement);
        }
        return false;
    }

    @Override
    public List<Administrator> findAllAdministrators() throws DaoException {
        LOGGER.log(Level.DEBUG, "Administrator DAO: start findAllAdministrators");
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<Administrator> administrators = new ArrayList<>();
        try {
            statement = connection.prepareStatement(FIND_ALL_ADMINS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                administrators.add(createAdminByResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Administrator DAO: finish findAllAdministrators");
            connectionPool.putBack(connection, resultSet, statement);
        }
        return administrators;

    }

    @Override
    public void deleteAdministrator(int idAdmin) throws DaoException {
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        LOGGER.log(Level.DEBUG, "Administrator DAO: start deleteAdministrator");
        try {
            statement = connection.prepareStatement(DELETE_ADMIN);
            statement.setInt(1, idAdmin);
            if (statement.executeUpdate() != 0) {
                return;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Administrator DAO: finish deleteAdministrator");
            connectionPool.putBack(connection, resultSet, statement);
        }

    }


    @Override
    public void deleteUser(int idAdmin) throws DaoException {
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        LOGGER.log(Level.DEBUG, "Administrator DAO: start deleteUser");
        try {
            statement = connection.prepareStatement(DELETE_USER);
            statement.setInt(1, idAdmin);
            if (statement.executeUpdate() != 0) {
                return;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Administrator DAO: finish deleteUser");
            connectionPool.putBack(connection, resultSet, statement);
        }

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
            throw new DaoException("Exception while executing SQL query",e);
        }
        return administrator;
    }



    @Override
    public void close(ResultSet resultSet, Statement statement) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
