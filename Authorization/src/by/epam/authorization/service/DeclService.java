package by.epam.authorization.service;

import java.util.ArrayList;

import by.epam.authorization.entity.Declaration;
import by.epam.authorization.service.exception.ServiceException;

public interface DeclService extends Service{
	public Declaration exDeclarationRequest (String declNumber) throws ServiceException;
	public Declaration declarationRequest (String declNumber) throws ServiceException;
	public Declaration adminDeclarationRequest (String declNumber) throws ServiceException;
	public String declarationSubmission (Declaration newDeclaration) throws ServiceException;
	public ArrayList<Declaration> userDeclarationListRequest(String UTN) throws ServiceException;
	public ArrayList<Declaration> adminDeclarationListRequest(String status) throws ServiceException;
	public void declarationChangingSubmission (Declaration changingDeclaration) throws ServiceException;
	public void declarationStatusChange (String declNumber, String status) throws ServiceException;

}
