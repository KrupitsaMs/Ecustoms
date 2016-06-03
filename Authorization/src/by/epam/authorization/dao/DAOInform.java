package by.epam.authorization.dao;

import by.epam.authorization.dao.con_pool.exception.ConnectionPoolException;

public interface DAOInform extends DAO{
	public String mailCheck (String mail)throws ConnectionPoolException;
	public String maxDeclarationNumberRequest()throws ConnectionPoolException;
}
