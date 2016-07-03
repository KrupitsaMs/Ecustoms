package by.epam.authorization.dao.conpool.db;

import java.util.ResourceBundle;

/**
 * DBResourceManager.java
 * It used in chain with DBParameter class.
 * It read values of properties of database connection in *.properties file.
 * @author MasSword
 */

public class DBResourceManager {
	private final static DBResourceManager instance = new DBResourceManager();
	private ResourceBundle bundle = ResourceBundle.getBundle("resources/prop");

	 public static DBResourceManager getInstance() {
	    return instance;
	 }

	 public String getValue(String key) {
	    return bundle.getString(key);
	 }
}
