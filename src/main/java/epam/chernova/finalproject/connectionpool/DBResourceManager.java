package epam.chernova.finalproject.connectionpool;

import java.util.ResourceBundle;

public class DBResourceManager {
    private static final DBResourceManager INSTANCE = new DBResourceManager();
    private ResourceBundle bundle = ResourceBundle.getBundle("/db/db");

    public DBResourceManager() {
    }

    public static DBResourceManager getInstance() {
        return INSTANCE;
    }

    public String getValue(String key){
        return bundle.getString(key);
    }
}
