package by.epam.authorization.service.impl;

import java.util.ArrayList;

import by.epam.authorization.dao.DAOFactory;
import by.epam.authorization.dao.DAOName;
import by.epam.authorization.dao.DAOUser;
import by.epam.authorization.dao.con_pool.exception.ConnectionPoolException;
import by.epam.authorization.entity.User;
import by.epam.authorization.service.UserService;
import by.epam.authorization.service.exception.ServiceException;

public class LoginService implements UserService{
	private final static String LOGIN_CHECK = "[A-Za-z\\d]*";

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
			throw new ServiceException("Something going wrong");
		}
	}
	
	private static boolean loginValidator(String login, String password){
		if(login.isEmpty() || !(login.matches(LOGIN_CHECK))){
			return false;
		}
		if(password.isEmpty() || !(login.matches(LOGIN_CHECK))){
			return false;
		}
		return true;
	}
	
	@Override
	public ArrayList<User> userEnquery() throws ServiceException {
		try{
			DAOUser check = DAOFactory.getInstance().getDAOUser(DAOName.USER);
			ArrayList<User> userList = check.getUserList();
			return userList;
		}  catch (ConnectionPoolException e) {
			throw new ServiceException("Something going wrong");
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
}
