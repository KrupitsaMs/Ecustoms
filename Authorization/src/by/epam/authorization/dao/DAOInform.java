package by.epam.authorization.dao;

import by.epam.authorization.dao.conpool.exception.ConnectionPoolException;

/**
 * DAOInform.java
 * Interface class defines supporting methods on DAO level 
 * @author MasSword
 */

public interface DAOInform extends DAO{
	String mailCheck (String mail)throws ConnectionPoolException;
	String maxDeclarationNumberRequest()throws ConnectionPoolException;
}
