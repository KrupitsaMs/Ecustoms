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

/**
 * UserDAOTest.java
 * Class provides testing of UserDAO.java
 * @author MasSword
 */

public class UserDAOTest {
	private static UserDAO userDAO;
	private Logger LOG = LogManager.getLogger(HttpServlet.class.getName());
	
	/**
     * Method initializes UserDAO userDAO
     */
	
	@BeforeClass
	public static void initUserDAO(){
		userDAO = (UserDAO) DAOFactory.getInstance().getDAOUser(DAOName.USER);
	}

	/**
     * Check if method checkUser returns message "Confirmed"
     * when it get as a parameter "Epam", "Epam"
     */
	
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
	
	/**
     * Check if method adminAddNewUser successfully creates new User in Service
     */
	
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
	
	/**
     * Check if method addNewUserTest successfully creates new User in Service
     */
	
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
	
	/**
     * Check if method getUserList successfully returns list of all Users in service
     */
	
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
