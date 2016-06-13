package by.epam.authorization.dao;

import by.epam.authorization.dao.conpool.exception.ConnectionPoolException;

public interface DAOInform extends DAO{
	public String mailCheck (String mail)throws ConnectionPoolException;
	public String maxDeclarationNumberRequest()throws ConnectionPoolException;
}
