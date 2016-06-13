package by.epam.authorization.service.impl;

import java.util.ArrayList;

import by.epam.authorization.dao.DAODecl;
import by.epam.authorization.dao.DAOFactory;
import by.epam.authorization.dao.DAOName;
import by.epam.authorization.dao.conpool.exception.ConnectionPoolException;
import by.epam.authorization.entity.Declaration;
import by.epam.authorization.service.DeclService;
import by.epam.authorization.service.exception.ServiceException;

public class DeclarationSubmissionService implements DeclService{
	
	@Override
	public String declarationSubmission(Declaration newDeclaration) throws ServiceException {
		try {
			if (!declarationValidator(newDeclaration)){
				return null;
			} else{
				DAODecl check = DAOFactory.getInstance().getDAODecl(DAOName.DECLARATION);
				String newDeclNumber =  check.declarationSubmission(newDeclaration);
				return newDeclNumber;
			}
		} catch (ConnectionPoolException e) {
			throw new ServiceException("Something going wrong");
		}
	}
	
	

	private static boolean declarationValidator(Declaration newDeclaration){
		if(newDeclaration == null){
			return false;
		}
		return true;
	}
	
	@Override
	public void declarationChangingSubmission(Declaration changingDeclaration) throws ServiceException {
		try{
		    DAODecl check = DAOFactory.getInstance().getDAODecl(DAOName.DECLARATION);
			check.changingDeclaration(changingDeclaration);
	    } catch (ConnectionPoolException e) {
		     throw new ServiceException("Something going wrong");
	    }
	}
	
	@Override
	public Declaration exDeclarationRequest(String declNumber)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Declaration declarationRequest(String declNumber)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Declaration> userDeclarationListRequest(String UTN)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Declaration adminDeclarationRequest(String declNumber)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ArrayList<Declaration> adminDeclarationListRequest(String status)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void declarationStatusChange(String declNumber, String status)
			throws ServiceException {
		// TODO Auto-generated method stub
		
	}
}
