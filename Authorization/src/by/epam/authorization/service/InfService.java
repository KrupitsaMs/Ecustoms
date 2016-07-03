package by.epam.authorization.service;

import by.epam.authorization.service.exception.ServiceException;

/**
 * InfService.java
 * Interface class defines supporting methods on Service level
 * without the use of entities
 * @author MasSword
 */

public interface InfService extends Service {
	String mailRequest(String mail)throws ServiceException;
	String maxDeclarationNumberRequest() throws ServiceException;

}
