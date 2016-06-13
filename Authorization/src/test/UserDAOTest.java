package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import by.epam.authorization.dao.DAOFactory;
import by.epam.authorization.dao.DAOName;
import by.epam.authorization.dao.conpool.exception.ConnectionPoolException;
import by.epam.authorization.dao.impl.UserDAO;
import by.epam.authorization.entity.User;

public class UserDAOTest {
	private static UserDAO userDAO;
	private Logger LOG = LogManager.getLogger(HttpServlet.class.getName());
	
	@BeforeClass
	public static void initUserDAO(){
		userDAO = (UserDAO) DAOFactory.getInstance().getDAOUser(DAOName.USER);
	}

	@Test
	public void checkUserTest() {
		try{
			User user = userDAO.checkUser("Epam", "Epam");
			assertEquals("Epam", user.getName());
			assertEquals("Epam", user.getPassword());
			assertEquals("confirmed", user.getStatus());
		} catch (ConnectionPoolException e) {
			LOG.error("Some truble in code", e);
		}
		
	}
	
	@Test
	public void adminAddNewUserTest() {
		try{
			
			userDAO.adminAddNewUser("Renault", "Renault", "user", "123321123", "Renault Belarus", "Makaenka 9", "renault@renault.by");
			User user = userDAO.checkUser("Renault", "Renault");
			assertEquals("Renault", user.getName());
			assertEquals("confirmed", user.getStatus());
			assertEquals("123321123", user.getUTN());
		} catch (ConnectionPoolException e) {
			LOG.error("Some truble in code", e);
		} finally{
			try {
				userDAO.removeUser("Renault");
			} catch (ConnectionPoolException e) {
				LOG.error("Some truble in code", e);
			}
		}
		
	}
	
	@Test
	public void addNewUserTest() {
		try{
			
			userDAO.addNewUser("Renault", "Renault", "123321123", "Renault Belarus", "Makaenka 9", "renault@renault.by");
			User user = userDAO.checkUser("Renault", "Renault");
			assertEquals(null, user);
		} catch (ConnectionPoolException e) {
			LOG.error("Some truble in code", e);
		} finally{
			try {
				userDAO.removeUser("Renault");
			} catch (ConnectionPoolException e) {
				LOG.error("Some truble in code", e);
			}
		}
		
	}
	
	@Test
	public void getUserListTest() {
		try{
			ArrayList<User> userList = userDAO.getUserList();
			assertEquals("Epam", userList.get(0).getName());
			assertEquals("Novikov", userList.get(6).getName());
		} catch (ConnectionPoolException e) {
			LOG.error("Some truble in code", e);
		}
		
	}

}
