package by.epam.authorization.service;

import by.epam.authorization.service.exception.ServiceException;

public interface InfService extends Service {
	public String mailRequest(String mail)throws ServiceException;
	public String maxDeclarationNumberRequest() throws ServiceException;

}
