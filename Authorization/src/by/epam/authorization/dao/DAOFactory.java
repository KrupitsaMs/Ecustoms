package by.epam.authorization.dao;

import java.util.HashMap;
import java.util.Map;

import by.epam.authorization.dao.conpool.impl.ConnectionPool;
import by.epam.authorization.dao.impl.DeclarationDAO;
import by.epam.authorization.dao.impl.InformDAO;
import by.epam.authorization.dao.impl.UserDAO;

/**
 * DAOFactory.java
 * Class-factory for classes that implemented interface DAO.java
 * @author MasSword
 */

public final class DAOFactory {
	private static DAOFactory instance = null;
	private Map <DAOName, DAO> daoMap = new HashMap<>();
	
	private DAOFactory(){
		daoMap.put(DAOName.USER, new UserDAO());
		daoMap.put(DAOName.INFORM, new InformDAO());
		daoMap.put(DAOName.DECLARATION, new DeclarationDAO());
	}

	/**
     * Method returns DAOFactory.java object
     * if it is not created, method creates it 
     * @return DAOFactory.java object
     */
	
	public static DAOFactory getInstance(){
		if (instance == null) {
			synchronized (ConnectionPool.class) {
				if (instance == null) {
					instance = new DAOFactory();
				}
			}
		}
		return instance;
	}
	
	/**
	 * getter-method
     * @return DAOUser.java object
     */
	
	public DAOUser getDAOUser(DAOName daoName){
		DAOUser currentDAO = (DAOUser) daoMap.get(daoName);
		return currentDAO;
	}
	
	/**
	 * getter-method
     * @return DAOInform.java object
     */
	
	public DAOInform getDAOInform(DAOName daoName){
		DAOInform currentDAO = (DAOInform) daoMap.get(daoName);
		return currentDAO;
	}
	
	/**
	 * getter-method
     * @return DAODecl.java object
     */
	
	public DAODecl getDAODecl(DAOName daoName){
		DAODecl currentDAO = (DAODecl) daoMap.get(daoName);
		return currentDAO;
	}
}
