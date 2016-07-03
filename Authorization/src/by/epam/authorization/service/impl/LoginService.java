package by.epam.authorization.service.impl;

import java.util.ArrayList;

import by.epam.authorization.dao.DAOFactory;
import by.epam.authorization.dao.DAOName;
import by.epam.authorization.dao.DAOUser;
import by.epam.authorization.dao.conpool.exception.ConnectionPoolException;
import by.epam.authorization.entity.User;
import by.epam.authorization.service.UserService;
import by.epam.authorization.service.exception.ServiceException;

/**
 * LoginService.java
 * Class on Service level which provides operations with User.java's objects except registration of new users. 
 * @author MasSword
 */

public class LoginService implements UserService{
	private final static String LOGIN_CHECK = "[A-Za-z\\d]*";

	/**
     * Method checks if user with parameter login
     * is situated in database.
     * If it's true, method returns User.java object
     * @param String login, String password
     * @return User.java's object
     */
	
	@Override
	public User userEnquery(String login, String password) throws ServiceException {
		try {
		if (!loginValidator(login, password)){
			return null;
		} 
		DAOUser check = DAOFactory.getInstance().getDAOUser(DAOName.USER);
		User newUser =  check.checkUser(login, password);
			return newUser;
		} catch (ConnectionPoolException e) {
			throw new ServiceException("unable to check the login", e);
		}
	}
	
	/**
     * Method analyzes if parameters is not null
     * if one of the parameters is null, method returns false,
     * if one of the parameters is not null, method returns true
     * @param String login, String password
     * @return boolean instance
     */
	
	private static boolean loginValidator(String login, String password){
		if(login.isEmpty() || !(login.matches(LOGIN_CHECK))){
			return false;
		}
		if(password.isEmpty() || !(login.matches(LOGIN_CHECK))){
			return false;
		}
		return true;
	}
	
	/**
     * Method return list of all user in DataBase
     * @return ArrayList<User>
     */
	
	@Override
	public ArrayList<User> userEnquery() throws ServiceException {
		try{
			DAOUser check = DAOFactory.getInstance().getDAOUser(DAOName.USER);
			ArrayList<User> userList = check.getUserList();
			return userList;
		}  catch (ConnectionPoolException e) {
			throw new ServiceException("unable to return the list of declaration", e);
		}

	}

	@Override
	public User userEnquery(String login, String password, String utn,
			String organizationName, String address, String mail) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void userStatusUpdate(User updatedUser, String status)
			throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User adminUserEnquery(String login, String password, String role,
			String utn, String organizationName, String address, String mail)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
}
