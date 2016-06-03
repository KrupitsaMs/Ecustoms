package by.epam.authorization.command.impl;

import javax.servlet.http.HttpServletRequest;
import by.epam.authorization.command.Command;
import by.epam.authorization.command.exception.CommandException;
import by.epam.authorization.controller.PageName;

public class LocalizationCommand implements Command{
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page;
		request.getSession(true).setAttribute("command", request.getParameter("command"));
		page = PageName.INDEX_PAGE;
		return page;
	}

}
