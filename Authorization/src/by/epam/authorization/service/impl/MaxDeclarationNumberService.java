package by.epam.authorization.service.impl;

import by.epam.authorization.dao.DAOFactory;
import by.epam.authorization.dao.DAOInform;
import by.epam.authorization.dao.DAOName;
import by.epam.authorization.dao.con_pool.exception.ConnectionPoolException;
import by.epam.authorization.service.InfService;
import by.epam.authorization.service.exception.ServiceException;

public class MaxDeclarationNumberService implements InfService{
	
	@Override
	public String maxDeclarationNumberRequest() throws ServiceException {
		try {
				DAOInform check = DAOFactory.getInstance().getDAOInform(DAOName.INFORM);
				String maxDeclNumber = check.maxDeclarationNumberRequest();
				return maxDeclNumber;
			}
		catch (ConnectionPoolException e) {
			throw new ServiceException("Something going wrong");
		}	
	}

	@Override
	public String mailRequest(String mail) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
}
