package test;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import by.epam.authorization.dao.DAOFactory;
import by.epam.authorization.dao.DAOName;
import by.epam.authorization.dao.conpool.exception.ConnectionPoolException;
import by.epam.authorization.dao.impl.InformDAO;

/**
 * InformDAOTest.java
 * Class provides testing of InformDAO.java
 * @author MasSword
 */

public class InformDAOTest {
	private static InformDAO informDAO;
	private Logger LOG = LogManager.getLogger(HttpServlet.class.getName());

	/**
     * Method initializes InformDAO informDAO
     */
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		informDAO = (InformDAO) DAOFactory.getInstance().getDAOInform(DAOName.INFORM);
	}

	/**
     * Check if method mailCheck returns message "Confirmed"
     * when it get as a parameter "epam@gmail.com"
     */
	
	@Test
	public void mailCheckTest() {
		try{
			String mailCheckResult = informDAO.mailCheck("epam@gmail.com");
			assertEquals("Confirmed", mailCheckResult);
			
		} catch (ConnectionPoolException e) {
			LOG.error("Some truble in code", e);
		}
		
	}
	
	/**
     * Check if method maxDeclarationNumberRequest returns "13".
     * This method has expected result only if in Database 13 declarations
     */
	
	@Test
	public void maxDeclarationNumberRequestTest() {
		try{
			String number = informDAO.maxDeclarationNumberRequest();
			assertEquals("13", number);
			
		} catch (ConnectionPoolException e) {
			LOG.error("Some truble in code", e);
		}
		
	}

}
