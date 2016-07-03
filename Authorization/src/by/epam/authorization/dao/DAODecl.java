package by.epam.authorization.dao;

import java.util.ArrayList;

import by.epam.authorization.dao.conpool.exception.ConnectionPoolException;
import by.epam.authorization.entity.Declaration;

/**
 * DAODecl.java
 * Interface class defines methods which manipulating  with objects of Declaration.java and DAO level 
 * @author MasSword
 */

public interface DAODecl extends DAO{
	Declaration declarationRequest (String declNumber) throws ConnectionPoolException;
	Declaration exDeclarationRequest (String declNumber) throws ConnectionPoolException;
	Declaration adminDeclarationRequest (String declNumber) throws ConnectionPoolException;
	String declarationSubmission (Declaration newDeclaration) throws ConnectionPoolException;
	ArrayList<Declaration> userDeclarationListRequest(String UTN) throws ConnectionPoolException;
	ArrayList<Declaration> adminDeclarationListRequest(String status) throws ConnectionPoolException;
	void changingDeclaration (Declaration changingDeclaration) throws ConnectionPoolException;
	void declarationStatusChange(String declNumber, String status) throws ConnectionPoolException;
}
