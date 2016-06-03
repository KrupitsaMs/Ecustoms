package by.epam.authorization.service.impl;

import java.util.ArrayList;

import by.epam.authorization.dao.DAOFactory;
import by.epam.authorization.dao.DAOName;
import by.epam.authorization.dao.DAOUser;
import by.epam.authorization.dao.con_pool.exception.ConnectionPoolException;
import by.epam.authorization.entity.User;
import by.epam.authorization.service.UserService;
import by.epam.authorization.service.exception.ServiceException;

public class RegistrationService implements UserService{
	private final static String LOGIN_CHECK = "[A-Za-z\\d]*";
	private final static String UTN_CHECK = "[\\d]{9}";
	private final static String ORG_NAME_CHECK = "[A-Za-z\\d\\s]*";
	private final static String ADDRESS_CHECK = "[A-Za-z\\d\\s\\p{Punct}]*";
	private final static String MAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

	@Override
	public User userEnquery(String login, String password, String utn,
			String organizationName, String address, String mail) throws ServiceException {
		try {
			if (!loginValidator(login, password, utn, organizationName, address, mail)){
				return null;
			} else{
				DAOUser check = DAOFactory.getInstance().getDAOUser(DAOName.USER);
				User newUser =  check.addNewUser(login, password, utn, organizationName, address, mail);
				return newUser;
			}
		} catch (ConnectionPoolException e) {
			throw new ServiceException("Something going wrong");
		}
	}
	
	private static boolean loginValidator(String login, String password, String utn,
			String organizationName, String address, String mail){
		if(login.isEmpty() || !(login.matches(LOGIN_CHECK))){
			return false;
		}
		if(password.isEmpty() || !(password.matches(LOGIN_CHECK))){
			return false;
		}
		if(utn.isEmpty() || !(utn.matches(UTN_CHECK))){
			return false;
		}
		if(organizationName.isEmpty() || !(organizationName.matches(ORG_NAME_CHECK))){
			return false;
		}
		if(address.isEmpty() || !(address.matches(ADDRESS_CHECK))){
			return false;
		}
		if(mail.isEmpty() || !(mail.matches(MAIL))){
			return false;
		}
		return true;
	}

	@Override
	public void userStatusUpdate(User updatedUser, String status) throws ServiceException {
		try {
				DAOUser check = DAOFactory.getInstance().getDAOUser(DAOName.USER);
				check.userStatusUpdate(updatedUser, status);
			} catch (ConnectionPoolException e) {
			throw new ServiceException("Something going wrong");
			}
		
	}
	
	@Override
	public User userEnquery(String login, String password)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<User> userEnquery() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
}
