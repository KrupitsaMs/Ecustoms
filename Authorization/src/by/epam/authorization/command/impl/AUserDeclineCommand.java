package by.epam.authorization.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.authorization.command.Command;
import by.epam.authorization.command.exception.CommandException;
import by.epam.authorization.controller.PageName;
import by.epam.authorization.entity.User;
import by.epam.authorization.service.ServiceFactory;
import by.epam.authorization.service.ServiceName;
import by.epam.authorization.service.UserService;
import by.epam.authorization.service.exception.ServiceException;

/**
 * AUserConfirmCommand.java
 * Class implemented interface Command
 * Gives to Admin function to change users status
 * to "rejected"
 * It contains method execute
 * @author MasSword
 */

public class AUserDeclineCommand implements Command{
	private static final String NOT_EXAMINED_USER = "not_examined_user";
	private static final String STATUS = "rejected";
	
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
			User  updatingUser = (User) request.getSession().getAttribute(NOT_EXAMINED_USER);
			UserService service = ServiceFactory.getServiceFactory().getUserService(ServiceName.REGISTRATION);
			service.userStatusUpdate(updatingUser, STATUS);
			page = PageName.A_USERS_REVIEW_SUCC;
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return page;
	}

}
