package by.epam.authorization.service;

import java.util.ArrayList;

import by.epam.authorization.entity.User;
import by.epam.authorization.service.exception.ServiceException;

/**
 * UserService.java
 * Interface class defines methods which manipulating  with objects of User.java on Service level 
 * @author MasSword
 */

public interface UserService extends Service{
	User userEnquery(String login, String password)throws ServiceException;
	User userEnquery(String login, String password, String utn, String organizationName, String address, String mail) throws ServiceException;
	ArrayList<User> userEnquery()throws ServiceException;
	void userStatusUpdate(User updatedUser, String status) throws ServiceException;
	User adminUserEnquery(String login, String password, String role, String utn, String organizationName, String address, String mail) throws ServiceException;
}
