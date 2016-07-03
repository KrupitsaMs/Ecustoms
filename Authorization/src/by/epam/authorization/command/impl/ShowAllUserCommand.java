package by.epam.authorization.command.impl;

import java.util.ArrayList;

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
 * ShowAllUserCommand.java
 * Class implemented interface Command
 * This class shows all users
 * It's a part of admin's interface 
 * It contains method execute
 * @author MasSword
 */

public class ShowAllUserCommand implements Command{
	
	private static final String USER_LIST="user_list";

	/**
     * Method reads a command from the request
     * and processes it. The result will be given as
     * a forward page
     * @param request request to read the command from
     * @return forward page - String class object
     */
	
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		try{
			String page;
			UserService service = ServiceFactory.getServiceFactory().getUserService(ServiceName.LOGIN);
			ArrayList<User> userList = service.userEnquery();
			request.setAttribute(USER_LIST, userList);
			page = PageName.A_USER_LIST_SUCC;
			return page;	
		} catch (ServiceException e) {
			throw new CommandException(e);
		}

	}

}
