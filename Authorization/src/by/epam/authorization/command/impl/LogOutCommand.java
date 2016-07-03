package by.epam.authorization.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.authorization.command.Command;
import by.epam.authorization.command.exception.CommandException;
import by.epam.authorization.controller.PageName;

/**
 * LogOutCommand.java
 * Class implemented interface Command
 * This class provides the user with a way out of personal account
 * It contains method execute
 * @author MasSword
 */

public class LogOutCommand implements Command{
	private static final String LOGIN = "login";
	
	/**
     * Method reads a command from the request
     * and processes it. The result will be given as
     * a forward page
     * @param request request to read the command from
     * @return forward page - String class object
     */
	
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		request.getSession().removeAttribute(LOGIN);
		return PageName.INDEX_PAGE;
	}

}
