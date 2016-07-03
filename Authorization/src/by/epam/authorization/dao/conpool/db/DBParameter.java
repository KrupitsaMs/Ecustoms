package by.epam.authorization.dao.conpool.db;


/**
 * DBParameter.java
 * It used in chain with DBResourceManager class.
 * It defines names of properties of database connection in *.properties file.
 * @author MasSword
 */

public class DBParameter {
	private DBParameter(){}
    public static final String DB_DRIVER = "db.driver";
    public static final String DB_URL = "db.url";
    public static final String DB_USER = "db.user";
    public static final String DB_PASSWORD = "db.password";
    public static final String DB_POOL_SIZE = "db.poolsize";
}
