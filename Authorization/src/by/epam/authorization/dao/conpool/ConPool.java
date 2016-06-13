package by.epam.authorization.dao.conpool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import by.epam.authorization.dao.conpool.exception.ConnectionPoolException;

public interface ConPool {
	public void initPoolData() throws ConnectionPoolException;
	public Connection takeConnection() throws ConnectionPoolException;
	public void closeConnection(Connection con, Statement st, ResultSet rs) throws ConnectionPoolException;
}
