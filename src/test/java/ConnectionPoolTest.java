import epam.chernova.finalproject.dao.ext.ClientDao;
import epam.chernova.finalproject.exception.ConnectionPoolException;
import epam.chernova.finalproject.exception.DaoException;
import epam.chernova.finalproject.webenum.DBparameter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ConnectionPoolTest extends Assert {
    private String url;
    private String user;
    private String password;

    private ResourceBundle wrongBundle = ResourceBundle.getBundle("db/db_wrong_data");


    @Test(expectedExceptions = SQLException.class)
    public void shouldThrowExceptionWhenCanNotGetConnectionFromDB() throws SQLException{
        this.url = wrongBundle.getString(DBparameter.URL.getValue());
        this.user = wrongBundle.getString(DBparameter.USER.getValue());
        this.password = wrongBundle.getString(DBparameter.PASSWORD.getValue());
        DriverManager.getConnection(url, user, password);
    }

}
