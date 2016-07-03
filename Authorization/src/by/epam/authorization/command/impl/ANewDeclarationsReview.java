package by.epam.authorization.command.impl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import by.epam.authorization.command.Command;
import by.epam.authorization.command.exception.CommandException;
import by.epam.authorization.controller.PageName;
import by.epam.authorization.entity.Declaration;
import by.epam.authorization.service.DeclService;
import by.epam.authorization.service.ServiceFactory;
import by.epam.authorization.service.ServiceName;
import by.epam.authorization.service.exception.ServiceException;

/**
 * ANewDeclarationsReview.java
 * Class implemented interface Command
 * Command choose all registered users in service
 * It contains method execute
 * @author MasSword
 */

public class ANewDeclarationsReview implements Command{

	private static final String STATUS = "not yet examined";
	private static final String SEARCHED_DECLARATION = "searched_declaration";
	private static final String LIST_SIZE = "list_size";

	/**
     * Method reads a command from the request
     * and processes it. The result will be given as
     * a forward page
     * @param request request to read the command from
     * @return forward page - String class object
     */
	
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page;
		try {
			DeclService service = ServiceFactory.getServiceFactory().getDeclService(ServiceName.DECLARATION);
			ArrayList <Declaration> declarationList = service.adminDeclarationListRequest(STATUS);
			int listSize = declarationList.size()-1;
			System.out.println(listSize);
			if (listSize<0){
				page = PageName.A_DECLARATION_REVIEW_FAIL;
			} else{
				Declaration curDecl = declarationList.get(0);
				request.getSession().setAttribute(SEARCHED_DECLARATION, curDecl);
				request.getSession().setAttribute(LIST_SIZE, listSize);
				page = PageName.A_DECLARATION_REVIEW;
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return page;
	}

}
