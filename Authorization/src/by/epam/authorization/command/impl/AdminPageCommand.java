package by.epam.authorization.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.authorization.command.Command;
import by.epam.authorization.command.exception.CommandException;
import by.epam.authorization.controller.PageName;

/**
 * AdminPageCommand.java
 * Class implemented interface Command
 * Command helps to choose needed forward page
 * it depends on JSP's  tag value
 * <input type="hidden" name="button" value="" />
 * Defines commands of user's with status "admin" interface
 * It contains method execute
 * @author MasSword
 */

public class AdminPageCommand implements Command{
	
	private static final String BUTTON_NAME = "button";
	private static final String A_SHOW_DECLARATION = "a_show_declaration";
	private static final String A_REGISTRATION = "a_registration";
	
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
		case A_SHOW_DECLARATION:
			page = PageName.A_DECLARATION_SHOW;
			break;
		case A_REGISTRATION:
			page = PageName.A_REGISTRATION;
			break;
		default: page = null;
		}
		return page;
	}
}
