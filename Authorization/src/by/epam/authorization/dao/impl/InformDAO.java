package by.epam.authorization.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.authorization.dao.DAOInform;
import by.epam.authorization.dao.conpool.exception.ConnectionPoolException;
import by.epam.authorization.dao.conpool.impl.ConnectionPool;

public class InformDAO implements DAOInform{
	private final static String SQL_LOGINING = "SELECT * FROM ecustoms.users WHERE Mail = ?";
	private final static String SQL_MAX_DECL_NUMBER = "SELECT MAX(Registration_number) AS 'Number' FROM ecustoms.declarations";
	private final static String MAIL_CONFIRMATION = "Confirmed";
	private final static String NUMBER = "Number";
	private final static String MIN_NUMBER = "0";
	
	@Override
	public String mailCheck(String mail) throws ConnectionPoolException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ConnectionPool con = ConnectionPool.getInstance();
			connection = con.takeConnection();
			ps = connection.prepareStatement(SQL_LOGINING);
			ps.setString(1, mail);
			rs = ps.executeQuery();
			if (rs.next()){
				con.returnConnection(connection);
				return MAIL_CONFIRMATION;
			} else {
				return null;
			}
		} catch (SQLException ex){
            throw new ConnectionPoolException("SQL exception in InformDao", ex);
		} finally{
			try{
				if(ps!=null){
					ps.close();
				}
				if(rs!=null){
					rs.close();
				}
			} catch(SQLException sqlee) {
				throw new ConnectionPoolException("SQL exception in userDao", sqlee);
	        }
		}
	}
	@Override
	public String maxDeclarationNumberRequest() throws ConnectionPoolException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ConnectionPool con = ConnectionPool.getInstance();
			connection = con.takeConnection();
			ps = connection.prepareStatement(SQL_MAX_DECL_NUMBER);
			rs = ps.executeQuery();
			if (rs.next()){
				con.returnConnection(connection);
				String maxDeclNumber = rs.getString(NUMBER);
				return maxDeclNumber;
			} else {
				return MIN_NUMBER;
			}
		} catch (SQLException ex){
            throw new ConnectionPoolException("SQL exception in InformDao", ex);
		} finally{
			try{
				if(ps!=null){
					ps.close();
				}
				if(rs!=null){
					rs.close();
				}
			} catch(SQLException sqlee) {
				throw new ConnectionPoolException("SQL exception in userDao", sqlee);
	        }
		}
	}

}
