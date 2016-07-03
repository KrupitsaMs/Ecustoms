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
 * ADeclarationDecline.java
 * Class implemented interface Command
 * Command changes status of declaration to "rejected"
 * It contains method execute
 * @author MasSword
 */

public class ADeclarationDecline implements Command{

	private static final String STATUS = "rejected";
	private static final String SEARCHED_DECLARATION = "searched_declaration";
	
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
			Declaration updatingDeclration = (Declaration) request.getSession().getAttribute(SEARCHED_DECLARATION);
			DeclService service = ServiceFactory.getServiceFactory().getDeclService(ServiceName.DECLARATION);
			service.declarationStatusChange(updatingDeclration.getNumber(), STATUS);
			page = PageName.A_DECLARATION_REVIEW_SUCC;
		}catch (ServiceException e) {
			throw new CommandException(e);
		}
		return page;
	}

}
