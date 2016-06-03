package by.epam.authorization.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.epam.authorization.dao.DAOUser;
import by.epam.authorization.dao.con_pool.exception.ConnectionPoolException;
import by.epam.authorization.dao.con_pool.impl.ConnectionPool;
import by.epam.authorization.entity.User;

public class UserDAO implements DAOUser{
	private final static String SQL_LOGINING = "SELECT * FROM ecustoms.users WHERE Login = ? AND Password = ? AND Confirmation = 'confirmed'";
	private final static String SQL_LOGIN_CHECK = "SELECT * FROM ecustoms.users WHERE Login = ?";
	private final static String SQL_NEW_USER = "INSERT INTO ecustoms.users "
			+ "(Login, Password, UTN, Name, Address, Mail) VALUES (?,?,?,?,?,?)";
	private final static String SQL_USER_LIST = "SELECT * FROM ecustoms.users";
	private final static String SQL_USER_UPDATE = "UPDATE ecustoms.users set Confirmation = ?  WHERE UTN = ?";
	
	
	@Override
	 public User checkUser(String login, String password) throws ConnectionPoolException{
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			try {
				ConnectionPool con = ConnectionPool.getInstance();
				connection = con.takeConnection();
				ps = connection.prepareStatement(SQL_LOGINING);
				ps.setString(1, login);
				ps.setString(2, password);
				rs = ps.executeQuery();
				if (rs.next()){
					String role = rs.getString("Role");
					String UTN = rs.getString("UTN");
					String organizationName = rs.getString("Name");
					String mail = rs.getString("Mail");
					con.returnConnection(connection);
					return new User(login, role, UTN, organizationName, mail);
				} else {
					return null;
				}
			} catch (SQLException ex){
	            throw new ConnectionPoolException("SQL exception in userDao", ex);
			} finally{
				try{
					if(ps!=null){
						ps.close();
					}
					if(rs!=null){
						rs.close();
					}
				} catch(SQLException sqlee) {
		            sqlee.printStackTrace();
		        }
				
			}	
	 }
	@Override
	 public User addNewUser(String login, String password, String utn,
				String organizationName, String address, String mail) throws ConnectionPoolException{
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement pis = null;
		 try{
		 	ConnectionPool con = ConnectionPool.getInstance();
			connection = con.takeConnection();
			ps = connection.prepareStatement(SQL_LOGIN_CHECK);
			ps.setString(1, login);
			rs = ps.executeQuery();
			if (rs.next()){
				return null;
			} else{
				pis = connection.prepareStatement(SQL_NEW_USER);
				pis.setString(1, login);
				pis.setString(2, password);
				pis.setString(3, utn);
				pis.setString(4, organizationName);
				pis.setString(5, address);
				pis.setString(6, mail);
				pis.executeUpdate();
				con.returnConnection(connection);
				return new User(login);
				}
			} catch (SQLException ex){
	            throw new ConnectionPoolException("SQL exception in userDao", ex);
			} finally{
				try{
					if(ps!=null){
						ps.close();
					}
					if(rs!=null){
						rs.close();
					}
					if(pis!=null){
						pis.close();
					}
				} catch(SQLException sqlee) {
		            sqlee.printStackTrace();
		        }
			}
	 }
	@Override
	public ArrayList<User> getUserList() throws ConnectionPoolException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ArrayList<User> userList = new ArrayList<>();
			ConnectionPool con = ConnectionPool.getInstance();
			connection = con.takeConnection();
			ps = connection.prepareStatement(SQL_USER_LIST);
			rs = ps.executeQuery();
			while (rs.next()){
				User currentUser = new User();
				currentUser.setName(rs.getString(1));
				currentUser.setPassword(rs.getString(2));
				currentUser.setRole(rs.getString(3));
				currentUser.setUTN(rs.getString(4));
				currentUser.setOrganizationName(rs.getString(5));
				currentUser.setAddress(rs.getString(6));
				currentUser.setStatus(rs.getString(7));
				currentUser.setMail(rs.getString(8));
				userList.add(currentUser);
			}
			return userList;
		} catch (SQLException ex){
            throw new ConnectionPoolException("SQL exception in userDao", ex);
		} finally{
			try{
				if(ps!=null){
					ps.close();
				}
				if(rs!=null){
					rs.close();
				}
			} catch(SQLException sqlee) {
	            sqlee.printStackTrace();
	        }
	     }
	}
	@Override
	public void userStatusUpdate(User updatedUser, String status) throws ConnectionPoolException {
		Connection connection = null;
		PreparedStatement ps = null;
		try{
			String UTN = updatedUser.getUTN();
			ConnectionPool con = ConnectionPool.getInstance();
			connection = con.takeConnection();
			ps = connection.prepareStatement(SQL_USER_UPDATE);
			ps.setString(1, status);
			ps.setString(2, UTN);
			ps.executeUpdate();
		} catch (SQLException ex){
            throw new ConnectionPoolException("SQL exception in userDao", ex);
		} finally{
			try{
				if(ps!=null){
					ps.close();
				}
			} catch(SQLException sqlee) {
	            sqlee.printStackTrace();
	        }
	     }
	}
}
