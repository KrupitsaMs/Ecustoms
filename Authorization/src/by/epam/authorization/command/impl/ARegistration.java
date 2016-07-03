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
 * ARegistration.java
 * Class implemented interface Command
 * Registers a new User in service by admin
 * with opportunity to make creating user with the status of admin
 * It contains method execute
 * @author MasSword
 */

public class ARegistration implements Command{

	private static final String LOGIN = "newLogin";
	private static final String PASSWORD = "newPassword";
	private static final String ROLE = "newRole";
	private static final String UTN = "newUTN";
	private static final String ORGANIZATION_NAME = "newOrgName";
	private static final String ORGANIZATION_ADDRESS = "newAddres";
	private static final String MAIL = "newMail";
	
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
			UserService service = ServiceFactory.getServiceFactory().getUserService(ServiceName.REGISTRATION);
			User user = service.adminUserEnquery(request.getParameter(LOGIN), request.getParameter(PASSWORD), request.getParameter(ROLE), request.getParameter(UTN),
					request.getParameter(ORGANIZATION_NAME), request.getParameter(ORGANIZATION_ADDRESS), request.getParameter(MAIL));
			if (user != null){	
				page = PageName.A_REGISTRATION_SUCC;
			}else{
				page = PageName.A_REGISTRATION_FAIL;
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return page;
	}

}
