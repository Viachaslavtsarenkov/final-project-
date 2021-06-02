package by.tsarenkov.shop.dao.db;

import java.util.ResourceBundle;

public class DBResourceManager {

    private static final  DBResourceManager instance = new DBResourceManager();
    private ResourceBundle bundle = ResourceBundle.getBundle("db");

    public DBResourceManager() {}

    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
