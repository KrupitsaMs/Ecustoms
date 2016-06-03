package by.epam.authorization.service.impl;

import java.util.ArrayList;

import by.epam.authorization.dao.DAODecl;
import by.epam.authorization.dao.DAOFactory;
import by.epam.authorization.dao.DAOName;
import by.epam.authorization.dao.con_pool.exception.ConnectionPoolException;
import by.epam.authorization.entity.Declaration;
import by.epam.authorization.service.DeclService;
import by.epam.authorization.service.exception.ServiceException;

public class DeclarationService implements DeclService{

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
			throw new ServiceException("Something going wrong");
		}	
	}
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
			throw new ServiceException("Something going wrong");
		}
	}
	private boolean declNumberValidator(String declNumber) {
		if(declNumber.isEmpty()){
			return false;
		}
		return true;
	}
	@Override
	public ArrayList<Declaration> userDeclarationListRequest(String UTN) throws ServiceException {
	    try{
			DAODecl check = DAOFactory.getInstance().getDAODecl(DAOName.DECLARATION);
		    ArrayList<Declaration> declList= check.userDeclarationListRequest(UTN);
		    return declList;
	    } catch (ConnectionPoolException e) {
			throw new ServiceException("Something going wrong");
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
