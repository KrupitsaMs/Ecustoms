package by.epam.authorization.service.impl;

import java.util.ArrayList;

import by.epam.authorization.dao.DAODecl;
import by.epam.authorization.dao.DAOFactory;
import by.epam.authorization.dao.DAOName;
import by.epam.authorization.dao.conpool.exception.ConnectionPoolException;
import by.epam.authorization.entity.Declaration;
import by.epam.authorization.service.DeclService;
import by.epam.authorization.service.exception.ServiceException;

/**
 * DeclarationSubmissionService.java
 * Class on Service level which provides operations with Declaration.java's objects received as a parameter
 * of the class's methods. 
 * @author MasSword
 */

public class DeclarationSubmissionService implements DeclService{
	
	/**
     * Method gets a Declaration object as a parameter
     * adds it's declaration in database and returns
     * it's number
     * @param String declNumber
     * @return Declaration
     */
	
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
			throw new ServiceException("unable to add the declaration in database", e);
		}
	}
	
	/**
     * Method analyzes if parameter newDeclaration is not null
     * if newDeclaration is null, method returns false,
     * if newDeclaration is not null, method returns true
     * @param String declNumber
     * @return boolean instance
     */

	private static boolean declarationValidator(Declaration newDeclaration){
		if(newDeclaration == null){
			return false;
		}
		return true;
	}
	
	/**
     * Method gets a Declaration object as a parameter
     * makes amendments to this declaration in database
     * (it defines needed declaration by the parameter's Declaration number)
     * @param Declaration changingDeclaration
     */
	
	@Override
	public void declarationChangingSubmission(Declaration changingDeclaration) throws ServiceException {
		try{
		    DAODecl check = DAOFactory.getInstance().getDAODecl(DAOName.DECLARATION);
			check.changingDeclaration(changingDeclaration);
	    } catch (ConnectionPoolException e) {
		     throw new ServiceException("unable to make ammendments to the declaration", e);
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
