package by.epam.authorization.dao.con_pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import by.epam.authorization.dao.con_pool.exception.ConnectionPoolException;

public interface CPool {
	public void initPoolData() throws ConnectionPoolException;
	public Connection takeConnection() throws ConnectionPoolException;
	public void closeConnection(Connection con, Statement st, ResultSet rs) throws ConnectionPoolException;
}
