package by.epam.authorization.dao.conpool.impl;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.authorization.dao.conpool.ConPool;
import by.epam.authorization.dao.conpool.db.DBParameter;
import by.epam.authorization.dao.conpool.db.DBResourceManager;
import by.epam.authorization.dao.conpool.exception.ConnectionPoolException;

/**
 * ConnectionPoolException.class
 * Class implemented interface ConPool
 * provides work of pool of connections with database
 * @author MasSword
*/

public class ConnectionPool implements ConPool{
	private static ConnectionPool instance;
	private final static Logger logger = LogManager.getLogger(ConnectionPool.class.getName());
	private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayConQueue;

    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private ConnectionPool() {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        this.driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);
        this.url = dbResourceManager.getValue(DBParameter.DB_URL);
        this.user = dbResourceManager.getValue(DBParameter.DB_USER);
        this.password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);
        try {
            this.poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POOL_SIZE));
        } catch (NumberFormatException ex){
            poolSize = 5;
        }
    }
    
	/**
     * Method returns ConnectionPool object
     * if it is has not created, method creates it 
     * @return ConnectionPool object
     */
    
    public static ConnectionPool getInstance() throws ConnectionPoolException{
    	ConnectionPool localInstance = instance;
		if (localInstance == null) {
			synchronized (ConnectionPool.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new ConnectionPool();
					localInstance.initPoolData();
				}
			}
		}
		return localInstance;
    }
    
	/**
     * Method initializes parameters of ConnectionPool object 
     */

    public void initPoolData() throws ConnectionPoolException {
        try {
            Class.forName(driverName);
            givenAwayConQueue = new ArrayBlockingQueue<Connection>(poolSize);
            connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                PooledConnection pooledConnection = new PooledConnection(connection);
                connectionQueue.add(pooledConnection);
            }
        } catch (SQLException ex) {
        	logger.error("Something going wrong", ex);
            throw new ConnectionPoolException("SQL exception in connection pool", ex);
        } catch (ClassNotFoundException ex) {
        	logger.error("Something going wrong", ex);
            throw  new ConnectionPoolException("Can't find database driver class", ex);
        }
    }

    public void dispose() throws ConnectionPoolException {
        clearConnectionQueue();
    }

    private void clearConnectionQueue() throws ConnectionPoolException {
        closeConnectionsQueue(givenAwayConQueue);
		closeConnectionsQueue(connectionQueue);
    }

	/**
     * Method returns one Connection object from pool
     * @return Connection object
     */
    
    public Connection takeConnection() throws ConnectionPoolException{
        Connection connection = null;
        try {
            connection = connectionQueue.take();
            givenAwayConQueue.add(connection);
        } catch (InterruptedException ex){
        	logger.error("Something going wrong", ex);
            throw new ConnectionPoolException("Error connection to the data source", ex);
        }
        return connection;
    }
	/**
     * This method gets as a parameter object of class Connection
     * and method returns it to pool of connections
     * @parameter Connection con
     */
    public void returnConnection(Connection con) throws ConnectionPoolException{
    		connectionQueue.add(con);
            givenAwayConQueue.remove(con);
    }

	/**
     * This method closes connection with con, st, rs
     * @parameter Connection con
     * @parameter Statement st
     * @parameter ResultSet rs
     */
    
    public void closeConnection(Connection con, Statement st, ResultSet rs) throws ConnectionPoolException {
        try {
            con.close();
        } catch (SQLException ex) {
        	logger.error("Something going wrong", ex);
        	throw new ConnectionPoolException("Error connection to the data source", ex);
        }
        try {
            rs.close();
        } catch (SQLException ex) {
        	logger.error("Something going wrong", ex);
        	throw new ConnectionPoolException("Error connection to the data source", ex);
        }
        try {
            st.close();
        } catch (SQLException ex) {
        	logger.error("Something going wrong", ex);
        	throw new ConnectionPoolException("Error connection to the data source", ex);
        }
    }

	/**
     * This method closes connection with con, st
     * @parameter Connection con
     * @parameter Statement st
     */
    
    public void closeConnection(Connection con, Statement st) throws ConnectionPoolException {
        try {
            con.close();
        } catch (SQLException ex) {
        	logger.error("Something going wrong", ex);
        	throw new ConnectionPoolException("Error connection to the data source", ex);
        }
        try {
            st.close();
        } catch (SQLException ex) {
        	logger.error("Something going wrong", ex);
        	throw new ConnectionPoolException("Error connection to the data source", ex);
        }
    }
    
    //Method closes connections (objects of PooledConnection class) in BlockingQueue

    private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws ConnectionPoolException {
        try{
        	Connection connection;
            while ((connection = queue.poll()) != null) {
                if (!connection.getAutoCommit()) {
                    connection.commit();
                }
                ((PooledConnection) connection).reallyClose();
            }
        } catch (SQLException ex){
        	logger.error("Something going wrong", ex);
        	throw new ConnectionPoolException("Error connection to the data source", ex);
        }

    }

    // PooledConnection.java - internal class
    // Class implemented interface Connection
    // provides connection with database
    
    private class PooledConnection implements Connection {
        private Connection connection;

        public PooledConnection(Connection c) throws SQLException {
            this.connection = c;
            this.connection.setAutoCommit(true);
        }

        public void reallyClose() throws SQLException {
            connection.close();
        }

      //Method closes connection (objects of Connection class) in Queues
        
        @Override
        public void close() throws SQLException {
            if (connection.isClosed()) {
                throw new SQLException("Attempting to close closed connection.");
            }
            if (connection.isReadOnly()) {
                connection.setReadOnly(false);
            }
            if (!givenAwayConQueue.remove(this)) {
                throw new SQLException("Error deleting connection from the given away connections pool.");
            }
            if (!connectionQueue.offer(this)) {
                throw new SQLException("Error allocation connection in the pool.");
            }
        }

        @Override
        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }

        @Override
        public Savepoint setSavepoint(String name) throws SQLException {
            return connection.setSavepoint();
        }
        
        @Override
        public void commit() throws SQLException {
            connection.commit();
        }

        @Override
        public Array createArrayOf(String typeName, Object[] elements) throws SQLException{
            return connection.createArrayOf(typeName, elements);
        }

        @Override
        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }

        @Override
        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }

        @Override
        public String getClientInfo(String name) throws SQLException {
            return connection.getClientInfo(name);
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return connection.isWrapperFor(iface);
        }

        @Override
        public void setHoldability(int holdability) throws SQLException {
            connection.setHoldability(holdability);
        }

        @Override
        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }

        @Override
        public String nativeSQL(String sql) throws SQLException {
            return connection.nativeSQL(sql);
        }

        @Override
        public Statement createStatement(int resultSetType, int setConcurrency, int resultSetHoldability) throws SQLException {
            return connection.createStatement(resultSetType, setConcurrency, resultSetHoldability);
        }

        @Override
        public void setClientInfo(String name, String value) throws SQLClientInfoException {
            connection.setClientInfo(name, value);
        }

        @Override
        public void setClientInfo(Properties arg0) throws SQLClientInfoException {
            connection.setClientInfo(arg0);
        }

        @Override
        public boolean isValid(int timeout) throws SQLException{
            return connection.isValid(timeout);
        }

        @Override
        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }

        @Override
        public void setReadOnly(boolean readOnly) throws SQLException {
            connection.setReadOnly(readOnly);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return connection.prepareStatement(sql,autoGeneratedKeys);
        }

        @Override
        public CallableStatement prepareCall(String sql) throws SQLException {
            return connection.prepareCall(sql);
        }

        @Override
        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public void setNetworkTimeout(Executor arg0, int arg1) throws SQLException{
            connection.setNetworkTimeout(arg0, arg1);
        }

        @Override
        public void rollback() throws SQLException {
            connection.rollback();
        }

        @Override
        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }

        @Override
        public void setTransactionIsolation(int level) throws SQLException {
            connection.setTransactionIsolation(level);
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }

        @Override
        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }

        @Override
        public String getSchema() throws SQLException {
            return connection.getSchema();
        }

        @Override
        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }

        @Override
        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }

        @Override
        public void releaseSavepoint(Savepoint args0) throws SQLException{
            connection.releaseSavepoint(args0);
        }

        @Override
        public void setSchema(String schema) throws SQLException {
            connection.setSchema(schema);
        }

        @Override
        public void setCatalog(String catalog) throws SQLException {
            connection.setCatalog(catalog);
        }

        @Override
        public void rollback(Savepoint arg0) throws SQLException {
            connection.rollback(arg0);
        }

        @Override
         public PreparedStatement prepareStatement(String sql) throws SQLException {
            return connection.prepareStatement(sql);
        }

        @Override
        public PreparedStatement prepareStatement(String sql,int[] columnIndexes) throws SQLException {
            return connection.prepareStatement(sql, columnIndexes);
        }

        @Override
        public PreparedStatement prepareStatement(String sql,String[] columnNames) throws SQLException {
            return connection.prepareStatement(sql, columnNames);
        }

        @Override
        public PreparedStatement prepareStatement(String sql,int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public PreparedStatement prepareStatement(String sql,int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }

        @Override
        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return connection.createStruct(typeName, attributes);
        }

        @Override
        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> arg0) throws SQLException {
            connection.setTypeMap(arg0);
        }

        @Override
        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }

        @Override
        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }
        
        @Override
        public Clob createClob() throws SQLException {
            return connection.createClob();
        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return connection.getNetworkTimeout();
        }

        @Override
        public Statement createStatement(int resultSetType, int setConcurrency) throws SQLException {
            return connection.createStatement(resultSetType, setConcurrency);
        }

        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException {
            connection.setAutoCommit(autoCommit);
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return connection.unwrap(iface);
        }

        @Override
        public void abort(Executor arg0) throws SQLException {
            connection.abort(arg0);
        }
    }
}
