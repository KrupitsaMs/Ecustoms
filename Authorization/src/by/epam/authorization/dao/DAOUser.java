package by.epam.authorization.dao;

import java.util.ArrayList;

import by.epam.authorization.dao.con_pool.exception.ConnectionPoolException;
import by.epam.authorization.entity.User;

public interface DAOUser extends DAO{
	public User addNewUser(String login, String password, String utn,
			String organizationName, String address, String mail) throws ConnectionPoolException;
	public User checkUser(String login, String password) throws ConnectionPoolException;
	public ArrayList<User> getUserList() throws ConnectionPoolException;
	public void userStatusUpdate(User updatedUser, String status) throws ConnectionPoolException;
}
