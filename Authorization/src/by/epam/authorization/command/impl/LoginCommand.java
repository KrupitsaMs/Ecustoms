package by.epam.authorization.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.authorization.command.Command;
import by.epam.authorization.command.exception.CommandException;
import by.epam.authorization.controller.PageName;
import by.epam.authorization.entity.User;
import by.epam.authorization.service.UserService;
import by.epam.authorization.service.ServiceFactory;
import by.epam.authorization.service.ServiceName;
import by.epam.authorization.service.exception.ServiceException;

/**
 * LoginCommand.java
 * Class implemented interface Command
 * This class is designed to provide user authentication at the service
 * It contains method execute
 * @author MasSword
 */

public class LoginCommand implements Command{
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String USER ="user";
	private static final String ADMIN ="admin";
	
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
			UserService service = ServiceFactory.getServiceFactory().getUserService(ServiceName.LOGIN);
			User user = service.userEnquery(request.getParameter(LOGIN), request.getParameter(PASSWORD));
			if (user == null){
				page = PageName.ERROR_LOGGING;
			} else{
				String status = user.getRole();
				switch (status){
				case USER:
					request.getSession(true).setAttribute(LOGIN, user);
					page = PageName.USER_PAGE;
					break;
				case ADMIN:
					request.getSession(true).setAttribute(LOGIN, user);
					page = PageName.ADMIN_PAGE;
					break;
				default: page = PageName.ERROR_LOGGING;
				}
			}		
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return page;
	}

}
