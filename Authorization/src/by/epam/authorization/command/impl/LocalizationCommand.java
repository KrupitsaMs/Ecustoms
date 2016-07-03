package by.epam.authorization.command.impl;

import javax.servlet.http.HttpServletRequest;
import by.epam.authorization.command.Command;
import by.epam.authorization.command.exception.CommandException;
import by.epam.authorization.controller.PageName;

/**
 * LocalizationCommand.java
 * Class implemented interface Command
 * This class changes language of service
 * It contains method execute
 * Now it has only two language - English and Russian
 * @author MasSword
 */

public class LocalizationCommand implements Command{
	
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
		request.getSession(true).setAttribute("command", request.getParameter("command"));
		page = PageName.INDEX_PAGE;
		return page;
	}

}
