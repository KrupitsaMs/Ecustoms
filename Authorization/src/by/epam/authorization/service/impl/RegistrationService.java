package by.epam.authorization.service.impl;

import java.util.ArrayList;

import by.epam.authorization.dao.DAOFactory;
import by.epam.authorization.dao.DAOName;
import by.epam.authorization.dao.DAOUser;
import by.epam.authorization.dao.conpool.exception.ConnectionPoolException;
import by.epam.authorization.entity.User;
import by.epam.authorization.service.UserService;
import by.epam.authorization.service.exception.ServiceException;

public class RegistrationService implements UserService{
	private final static String LOGIN_CHECK = "[A-Za-z\\d]*";
	private final static String UTN_CHECK = "[\\d]{9}";
	private final static String ADMIN_UTN_CHECK = "[\\d]{1,3}";
	private final static String ORG_NAME_CHECK = "[A-Za-z\\d\\s]*";
	private final static String ADDRESS_CHECK = "[A-Za-z\\d\\s\\p{Punct}]*";
	private final static String MAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
	private final static String ADMIN = "admin";
	private final static String USER = "user";

	@Override
	public User userEnquery(String login, String password, String utn,
			String organizationName, String address, String mail) throws ServiceException {
		try {
			if (!loginValidator(login, password, organizationName, address, mail) || !UTNValidator (utn)){
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
	
	private static boolean loginValidator(String login, String password,
			String organizationName, String address, String mail){
		if(login.isEmpty() || !(login.matches(LOGIN_CHECK))){
			return false;
		}
		if(password.isEmpty() || !(password.matches(LOGIN_CHECK))){
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
	private static boolean adminValidator (String role, String utn){
		if(role.isEmpty() || !(role.equals(USER) || role.equals(ADMIN))){
			return false;
		}
		if(utn.isEmpty() || !(utn.matches(ADMIN_UTN_CHECK))){
			return false;
		}
		return true;
	}
	private static boolean UTNValidator (String utn){
		if(utn.isEmpty() || !(utn.matches(UTN_CHECK))){
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
	public User adminUserEnquery(String login, String password, String role, String utn, String organizationName, String address, String mail) throws ServiceException {
		try {
			if (!loginValidator(login, password, organizationName, address, mail) || !adminValidator (role, utn)){
				System.out.println("Валидация не пройдена");
				return null;
			} else{
				DAOUser check = DAOFactory.getInstance().getDAOUser(DAOName.USER);
				User newUser =  check.adminAddNewUser(login, password, role, utn, organizationName, address, mail);
				return newUser;
			}
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
