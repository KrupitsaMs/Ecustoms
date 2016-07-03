package by.epam.authorization.service;

import java.util.ArrayList;

import by.epam.authorization.entity.Declaration;
import by.epam.authorization.service.exception.ServiceException;

/**
 * DeclService.java
 * Interface class defines methods which manipulating  with objects of Declaration.java and Service level 
 * @author MasSword
 */

public interface DeclService extends Service{
	Declaration exDeclarationRequest (String declNumber) throws ServiceException;
	Declaration declarationRequest (String declNumber) throws ServiceException;
	Declaration adminDeclarationRequest (String declNumber) throws ServiceException;
	String declarationSubmission (Declaration newDeclaration) throws ServiceException;
	ArrayList<Declaration> userDeclarationListRequest(String UTN) throws ServiceException;
	ArrayList<Declaration> adminDeclarationListRequest(String status) throws ServiceException;
	void declarationChangingSubmission (Declaration changingDeclaration) throws ServiceException;
	void declarationStatusChange (String declNumber, String status) throws ServiceException;

}
