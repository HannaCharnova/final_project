package epam.chernova.finalproject.dao.ext;

import epam.chernova.finalproject.connectionpool.ConnectionPool;
import epam.chernova.finalproject.dao.IAccountDao;
import epam.chernova.finalproject.dao.IOrderDao;
import epam.chernova.finalproject.entity.Account;
import epam.chernova.finalproject.entity.Order;
import epam.chernova.finalproject.exception.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao implements IAccountDao {
    private static final Logger LOGGER = Logger.getLogger(AccountDao.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String FIND_ACCOUNT_BY_CLIENT_ID = "SELECT * FROM cafe.account WHERE cafe.account.client_user_iduser =?";
    private static final String PAY_ORDER = "UPDATE cafe.account SET cafe.account.credit = (cafe.account.credit - ?) WHERE cafe.account.client_user_iduser = ?";

    @Override
    public Account findAccountByClientId(int idClient) throws DaoException {
        LOGGER.log(Level.DEBUG, "Account DAO: start findAccountByClientId");
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_ACCOUNT_BY_CLIENT_ID);
            statement.setInt(1, idClient);
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                return createAccountByResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Account DAO: finish findAccountByClientId");
            connectionPool.putBack(connection, resultSet, statement);
        }
        return null;

    }

    @Override
    public void payOrder(int idClient, double totalCost) throws DaoException {
        LOGGER.log(Level.DEBUG, "Account DAO: start payOrder");
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(PAY_ORDER);
            statement.setDouble(1, totalCost);
            statement.setInt(2, idClient);
            if (statement.executeUpdate() != 0) {
                return;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        } finally {
            LOGGER.log(Level.DEBUG, "Account DAO: finish payOrder");
            connectionPool.putBack(connection, resultSet, statement);
        }
    }

    private Account createAccountByResultSet(ResultSet resultSet) throws DaoException {
        Account account;
        try {
            account = new Account();
            account.setIdAccount(resultSet.getInt("idaccount"));
            account.setCredit(resultSet.getDouble("credit"));
            account.setAccountNumber(resultSet.getString("account_number"));
            account.setIdClient(resultSet.getInt("client_user_iduser"));
        } catch (SQLException e) {
            throw new DaoException("Exception while executing SQL query", e);
        }
        return account;
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
