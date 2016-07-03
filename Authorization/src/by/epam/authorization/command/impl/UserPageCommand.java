package by.epam.authorization.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.authorization.command.Command;
import by.epam.authorization.command.exception.CommandException;
import by.epam.authorization.controller.PageName;

/**
 * UserPageCommand.java
 * Class implemented interface Command
 * Command helps to choose needed forward page
 * it depends on JSP's  tag value
 * <input type="hidden" name="button" value="" />
 * Defines commands of user's with status "user" interface
 * It contains method execute
 * @author MasSword
 */

public class UserPageCommand implements Command{
	private static final String BUTTON_NAME = "button";
	private static final String DECLARATION_CHECK = "declaration_check";
	private static final String USER_CHANGE_DECLARATION = "user_change_declaration";
	
	/**
     * Method reads a command from the request
     * and processes it. The result will be given as
     * a forward page
     * @param request request to read the command from
     * @return forward page - String class object
     */
	
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = null;
		String buttonName = request.getParameter(BUTTON_NAME);
		System.out.println(buttonName);
		switch(buttonName){
		case DECLARATION_CHECK:
			page = PageName.DECLARATION_CHECK;
			break;
		case USER_CHANGE_DECLARATION:
			page = PageName.DECLARATION_CHANGE_NUMBER;
			break;
		default: page = null;
		}
		return page;
	}

}
