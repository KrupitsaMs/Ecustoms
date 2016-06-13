package by.epam.authorization.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.authorization.command.Command;
import by.epam.authorization.command.exception.CommandException;
import by.epam.authorization.controller.PageName;

public class NoSuchCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page = PageName.ERROR_PAGE;
		return page;
	}

}
