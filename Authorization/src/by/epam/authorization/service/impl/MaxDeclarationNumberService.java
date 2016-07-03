package by.epam.authorization.service.impl;

import by.epam.authorization.dao.DAOFactory;
import by.epam.authorization.dao.DAOInform;
import by.epam.authorization.dao.DAOName;
import by.epam.authorization.dao.conpool.exception.ConnectionPoolException;
import by.epam.authorization.service.InfService;
import by.epam.authorization.service.exception.ServiceException;

/**
 * MaxDeclarationNumberService.java
 * Class that provides 	definition of current max number of declarations
 * @author MasSword
 */

public class MaxDeclarationNumberService implements InfService{
	
	/**
     * Method definition current max number of declarations in service and returns it
     * @return String instance
     */
	
	@Override
	public String maxDeclarationNumberRequest() throws ServiceException {
		try {
				DAOInform check = DAOFactory.getInstance().getDAOInform(DAOName.INFORM);
				String maxDeclNumber = check.maxDeclarationNumberRequest();
				return maxDeclNumber;
			}
		catch (ConnectionPoolException e) {
			throw new ServiceException("unable to return a max declaration's number", e);
		}	
	}

	@Override
	public String mailRequest(String mail) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
}
