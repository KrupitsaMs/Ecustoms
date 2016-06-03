package by.epam.authorization.service;

import java.util.ArrayList;

import by.epam.authorization.entity.User;
import by.epam.authorization.service.exception.ServiceException;

public interface UserService extends Service{
	public User userEnquery(String login, String password)throws ServiceException;
	public User userEnquery(String login, String password, String utn, String organizationName, String address, String mail) throws ServiceException;
	public ArrayList<User> userEnquery()throws ServiceException;
	public void userStatusUpdate(User updatedUser, String status) throws ServiceException;
}
