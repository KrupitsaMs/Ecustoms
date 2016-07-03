package by.epam.authorization.service.impl;

import by.epam.authorization.dao.DAOInform;
import by.epam.authorization.dao.DAOFactory;
import by.epam.authorization.dao.DAOName;
import by.epam.authorization.dao.conpool.exception.ConnectionPoolException;
import by.epam.authorization.service.InfService;
import by.epam.authorization.service.exception.ServiceException;

/**
 * RestoreService.java
 * Class provides 	interface of restoring forgotten password of an account
 * to login in service
 * @author MasSword
 */

public class RestoreService implements InfService{
	private final static String MAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

	/**
     * Method gets a e-mail address as a parameter
     * and check if it's is situated in Database 
     * If it's true, method returns String message
     * @param String mail
     * @return String message
     */
	
	@Override
	public String mailRequest(String mail) throws ServiceException {
		try {
			if (!mailValidator(mail)){
				return null;
			} else{
				DAOInform check = DAOFactory.getInstance().getDAOInform(DAOName.INFORM);
				String mailConfirmation = check.mailCheck(mail);
				return mailConfirmation;
			}  
		} catch (ConnectionPoolException e) {
			throw new ServiceException("unable to check the mail of the user", e);
		}	
	}
	
	/**
     * Method analyzes if mail is not null
     * if mail is null, method returns false,
     * if mail is not null, method returns true
     * @param String mail
     * @return boolean instance
     */
	
	private boolean mailValidator(String mail) {
		if(mail.isEmpty() || !(mail.matches(MAIL))){
			return false;
		}
		return true;
	}
	@Override
	public String maxDeclarationNumberRequest() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
