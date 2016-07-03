package by.epam.authorization.dao;

import java.util.ArrayList;

import by.epam.authorization.dao.conpool.exception.ConnectionPoolException;
import by.epam.authorization.entity.User;

/**
 * DAOUser.java
 * Interface class defines methods which manipulating  with objects of User.java on DAO level 
 * @author MasSword
 */

public interface DAOUser extends DAO{
	User addNewUser(String login, String password, String utn,
			String organizationName, String address, String mail) throws ConnectionPoolException;
	User adminAddNewUser(String login, String password, String role, String utn,
			String organizationName, String address, String mail) throws ConnectionPoolException;
	User checkUser(String login, String password) throws ConnectionPoolException;
	ArrayList<User> getUserList() throws ConnectionPoolException;
	void userStatusUpdate(User updatedUser, String status) throws ConnectionPoolException;
}
