package by.epam.authorization.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.authorization.command.Command;
import by.epam.authorization.command.exception.CommandException;
import by.epam.authorization.controller.PageName;

public class LogOutCommand implements Command{
	private static final String LOGIN = "login";
	
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		request.getSession().removeAttribute(LOGIN);
		return PageName.INDEX_PAGE;
	}

}
