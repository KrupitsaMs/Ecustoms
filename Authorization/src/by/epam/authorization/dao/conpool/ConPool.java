package by.epam.authorization.dao.conpool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import by.epam.authorization.dao.conpool.exception.ConnectionPoolException;

/**
 * ConPool.java
 * Interface class defines methods which provides work of pool of connections with database 
 * @author MasSword
 */

public interface ConPool {
	public void initPoolData() throws ConnectionPoolException;
	public Connection takeConnection() throws ConnectionPoolException;
	public void closeConnection(Connection con, Statement st, ResultSet rs) throws ConnectionPoolException;
}
