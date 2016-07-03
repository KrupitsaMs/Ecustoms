package by.epam.authorization.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.epam.authorization.dao.DAOUser;
import by.epam.authorization.dao.conpool.exception.ConnectionPoolException;
import by.epam.authorization.dao.conpool.impl.ConnectionPool;
import by.epam.authorization.entity.User;

/**
 * UserDAO.java
 * Class implemented interface DAOUser
 * Provides operations in Database and with objects of User.java
 * @author MasSword
 */

public class UserDAO implements DAOUser{
	private final static String SQL_LOGINING = "SELECT * FROM ecustoms.users WHERE Login = ? AND Password = ? AND Confirmation = 'confirmed'";
	private final static String SQL_LOGIN_CHECK = "SELECT * FROM ecustoms.users WHERE Login = ?";
	private final static String SQL_NEW_USER = "INSERT INTO ecustoms.users "
			+ "(Login, Password, UTN, Name, Address, Mail) VALUES (?,?,?,?,?,?)";
	private final static String SQL_ADMIN_NEW_USER = "INSERT INTO ecustoms.users "
			+ "(Login, Password, Role, UTN, Name, Address, Confirmation, Mail) VALUES (?,?,?,?,?,?,?,?)";
	private final static String SQL_USER_LIST = "SELECT * FROM ecustoms.users";
	private final static String SQL_USER_UPDATE = "UPDATE ecustoms.users set Confirmation = ?  WHERE UTN = ?";
	private final static String SQL_REMOVE_USER = "DELETE FROM ecustoms.users WHERE Login = ?";
	private final static String STATUS = "confirmed";
	
	/**
     * Method checks if user with parameter login
     * is situated in database.
     * If it's true, method returns User.java object
     * @param String login, String password
     * @return User
     */
	
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
					User currentUser = new User(login, role, UTN, organizationName, mail);
					currentUser.setPassword(password);
					currentUser.setStatus(STATUS);
					return currentUser;
				} else {
					return null;
				}
			} catch (SQLException ex){
	            throw new ConnectionPoolException("unable to check a user", ex);
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
	
	/**
     * Method adds new User in Database
     * @param String login, String password, String utn, String organizationName, String address, String mail
     * @return User
     */
	
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
	            throw new ConnectionPoolException("unable to add the user in database", ex);
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
					throw new ConnectionPoolException("SQL exception in userDao", sqlee);
		        }
			}
	 }
	
	/**
     * Method return list of all user in DataBase
     * @return ArrayList<User>
     */
	
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
            throw new ConnectionPoolException("unable to return list of users", ex);
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
	
	/**
     * Method changes status of user's account.
     * @param User updatedUser, String status
     */
	
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
            throw new ConnectionPoolException("unable to change the status of the user", ex);
		} finally{
			try{
				if(ps!=null){
					ps.close();
				}
			} catch(SQLException sqlee) {
				throw new ConnectionPoolException("SQL exception in userDao", sqlee);
	        }
	     }
	}
	
	/**
     * Method adds new User in Database
     * with  the possibility to determine the User's status
     * @param String login, String password, String role, String utn, String organizationName, String address, String mail
     * @return User
     */
	
	@Override
	public User adminAddNewUser(String login, String password, String role, String utn, String organizationName, String address, String mail) throws ConnectionPoolException {
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
				pis = connection.prepareStatement(SQL_ADMIN_NEW_USER);
				pis.setString(1, login);
				pis.setString(2, password);
				pis.setString(3, role);
				pis.setString(4, utn);
				pis.setString(5, organizationName);
				pis.setString(6, address);
				pis.setString(7, STATUS);
				pis.setString(8, mail);
				pis.executeUpdate();
				con.returnConnection(connection);
				User user = new User(login);
				user.setStatus(STATUS);
				user.setUTN(utn);
				return user;
				}
			} catch (SQLException ex){
	            throw new ConnectionPoolException("unable to add the user", ex);
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
					throw new ConnectionPoolException("SQL exception in userDao", sqlee);
		        }
			}
	}
	
	/**
     * Method removes user's account from Database
     * @param String userLogin
     * @return User
     */
	
	public void removeUser (String userLogin) throws ConnectionPoolException{
		Connection connection = null;
		PreparedStatement ps = null;
		try{
		 	ConnectionPool con = ConnectionPool.getInstance();
			connection = con.takeConnection();
			ps = connection.prepareStatement(SQL_REMOVE_USER);
			ps.setString(1, userLogin);
			ps.executeUpdate();
			con.returnConnection(connection);
		} catch (SQLException ex){
            throw new ConnectionPoolException("unable to remove the user from database", ex);
		} finally{
			try {
			if(ps!=null){
				ps.close();
				}
			} catch (SQLException e) {
				throw new ConnectionPoolException("SQL exception in userDao", e);
			}
		}	
		
	}
}
