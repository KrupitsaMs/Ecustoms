package by.epam.authorization.dao.con_pool.db;

import java.util.ResourceBundle;

public class DBResourceManager {
	private final static DBResourceManager instance = new DBResourceManager();
	private ResourceBundle bundle = ResourceBundle.getBundle("prop");

	 public static DBResourceManager getInstance() {
	    return instance;
	 }

	 public String getValue(String key) {
	    return bundle.getString(key);
	 }
}
