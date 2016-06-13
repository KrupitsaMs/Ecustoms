package by.epam.authorization.dao;

import java.util.ArrayList;

import by.epam.authorization.dao.conpool.exception.ConnectionPoolException;
import by.epam.authorization.entity.Declaration;

public interface DAODecl extends DAO{
	public Declaration declarationRequest (String declNumber) throws ConnectionPoolException;
	public Declaration exDeclarationRequest (String declNumber) throws ConnectionPoolException;
	public Declaration adminDeclarationRequest (String declNumber) throws ConnectionPoolException;
	public String declarationSubmission (Declaration newDeclaration) throws ConnectionPoolException;
	public ArrayList<Declaration> userDeclarationListRequest(String UTN) throws ConnectionPoolException;
	public ArrayList<Declaration> adminDeclarationListRequest(String status) throws ConnectionPoolException;
	public void changingDeclaration (Declaration changingDeclaration) throws ConnectionPoolException;
	public void declarationStatusChange(String declNumber, String status) throws ConnectionPoolException;
}
