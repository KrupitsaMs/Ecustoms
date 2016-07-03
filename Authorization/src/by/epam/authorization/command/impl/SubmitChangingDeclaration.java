package by.epam.authorization.command.impl;

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
 * SubmitChangingDeclaration.java
 * Class implemented interface Command
 * Provides submitting of amendments to the declaration
 * It contains method execute
 * @author MasSword
 */

public class SubmitChangingDeclaration implements Command{
	private static final String SEARCHED_DECL = "searched_decl";
	
	/**
     * Method reads a command from the request
     * and processes it. The result will be given as
     * a forward page
     * @param request request to read the command from
     * @return forward page - String class object
     */
	
	@Override
	public String execute(HttpServletRequest request) throws CommandException{
		String page;
		try {
			Declaration changingDeclaration = (Declaration) request.getSession().getAttribute(SEARCHED_DECL);
			DeclService service = ServiceFactory.getServiceFactory().getDeclService(ServiceName.DECLARATION_SUBMISSION);
			service.declarationChangingSubmission(changingDeclaration);
			page = PageName.DECLARATION_CHANGINGS_SUCCES;
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return page;
	}

}
