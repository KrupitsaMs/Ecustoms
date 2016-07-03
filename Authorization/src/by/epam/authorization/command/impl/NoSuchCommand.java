package by.epam.authorization.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.authorization.command.Command;
import by.epam.authorization.command.exception.CommandException;
import by.epam.authorization.controller.PageName;

/**
 * NoSuchCommand.java
 * Class implemented interface Command
 * This class is emergency class
 * If in the service user choose a nonexistent command, this class returns error page
 * It contains method execute
 * @author MasSword
 */

public class NoSuchCommand implements Command{

	/**
     * Method reads a command from the request
     * and processes it. The result will be given as
     * a forward page
     * @param request request to read the command from
     * @return forward page - String class object
     */
	
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = PageName.ERROR_PAGE;
		return page;
	}

}
