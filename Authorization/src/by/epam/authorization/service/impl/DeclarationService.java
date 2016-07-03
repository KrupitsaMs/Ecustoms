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
 * DeclarationService.java
 * Class on Service level which provides operations with returned Declaration.java's objects. 
 * @author MasSword
 */

public class DeclarationService implements DeclService{
	
	private final static String DECL_NUMBER = "[\\d]";

	/**
     * Method reads and processes declaration number
     * and if declaration's type is "EX"
     * returns Declaration.java's object, else - null
     * @param String declNumber
     * @return Declaration.java object
     */
	
	@Override
	public Declaration exDeclarationRequest(String declNumber) throws ServiceException {
		try {
			if (!declNumberValidator(declNumber)){
				return null;
			} else{
				DAODecl check = DAOFactory.getInstance().getDAODecl(DAOName.DECLARATION);
				Declaration exDeclaration = check.exDeclarationRequest(declNumber);
				return exDeclaration;
			}  
		} catch (ConnectionPoolException e) {
			throw new ServiceException("unable to check the ex declaration status", e);
		}	
	}
	
	/**
     * Method reads and processes declaration number
     * and if declaration is situated 
     * returns Declaration.java's object, else - null
     * @param String declNumber
     * @return Declaration.java object
     */
	
	@Override
	public Declaration declarationRequest(String declNumber)throws ServiceException {
		try {
			if (!declNumberValidator(declNumber)){
				return null;
			} else{
				DAODecl check = DAOFactory.getInstance().getDAODecl(DAOName.DECLARATION);
				Declaration declaration = check.declarationRequest(declNumber);
				return declaration;
			}  
		} catch (ConnectionPoolException e) {
			throw new ServiceException("unable to check the declaration", e);
		}
	}
	
	/**
     * Method analyzes if parameter declaration number is not null
     * if declaration number is null, method returns false,
     * if declaration number is not null, method returns true
     * @param String declNumber
     * @return boolean instance
     */
	private boolean declNumberValidator(String declNumber) {
		if(!(declNumber.matches(DECL_NUMBER))){
			return false;
		}
		return true;
	}
	
	/**
     * Method reads and processes unique tax payers number
     * and returns list of all companies declarations
     * @param String UTN
     * @return ArrayList<Declaration> list
     */
	
	@Override
	public ArrayList<Declaration> userDeclarationListRequest(String UTN) throws ServiceException {
	    try{
			DAODecl check = DAOFactory.getInstance().getDAODecl(DAOName.DECLARATION);
		    ArrayList<Declaration> declList= check.userDeclarationListRequest(UTN);
		    return declList;
	    } catch (ConnectionPoolException e) {
			throw new ServiceException("unable to return list of declarations", e);
		}
	}
	
	/**
     * Method reads and processes declaration number
     * and if declaration is situated 
     * returns Declaration.java's object, else - null
     * this method accessible only for users with status "admin"
     * @param String declNumber
     * @return Declaration.java object
     */
	
	@Override
	public Declaration adminDeclarationRequest(String declNumber) throws ServiceException {
		try {
			if (!declNumberValidator(declNumber)){
				return null;
			} else{
				DAODecl check = DAOFactory.getInstance().getDAODecl(DAOName.DECLARATION);
				Declaration declaration = check.adminDeclarationRequest(declNumber);
				return declaration;
			}  
		} catch (ConnectionPoolException e) {
			throw new ServiceException("unable to return a declaration", e);
		}
	}
	
	/**
     * Method gets a declaration status as a parameter
     * process it, and returns list of Declaration's with this status
     * @param String status
     * @return ArrayList<Declaration> list
     */
	
	@Override
	public ArrayList<Declaration> adminDeclarationListRequest(String status) throws ServiceException {
		try {
				DAODecl check = DAOFactory.getInstance().getDAODecl(DAOName.DECLARATION);
				ArrayList<Declaration> declarationList = check.adminDeclarationListRequest(status);
				return declarationList;
			}  catch (ConnectionPoolException e) {
			throw new ServiceException("unable to get list of declarations", e);
		}
	}

	/**
     * Method gets a declaration status  and number as a parameters
     * and changes status of declaration with this number in database
     * @param String status, String declNumber
     */
	
	@Override
	public void declarationStatusChange(String declNumber, String status) throws ServiceException {
		try {
			DAODecl check = DAOFactory.getInstance().getDAODecl(DAOName.DECLARATION);
			check.declarationStatusChange(declNumber, status);
		}  catch (ConnectionPoolException e) {
		throw new ServiceException("unable to change the declaration sstatus", e);
	}
		
	}
	@Override
	public String declarationSubmission(Declaration newDeclaration)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void declarationChangingSubmission(Declaration changingDeclaration)
			throws ServiceException {
		// TODO Auto-generated method stub
		
	}
}
