package by.epam.authorization.service.impl;

import by.epam.authorization.dao.DAOInform;
import by.epam.authorization.dao.DAOFactory;
import by.epam.authorization.dao.DAOName;
import by.epam.authorization.dao.con_pool.exception.ConnectionPoolException;
import by.epam.authorization.service.InfService;
import by.epam.authorization.service.exception.ServiceException;

public class RestoreService implements InfService{
	private final static String MAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

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
			throw new ServiceException("Something going wrong");
		}	
	}
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
