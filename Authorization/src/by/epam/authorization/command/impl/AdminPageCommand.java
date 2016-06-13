package by.epam.authorization.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.authorization.command.Command;
import by.epam.authorization.command.exception.CommandException;
import by.epam.authorization.controller.PageName;

public class AdminPageCommand implements Command{
	
	private static final String BUTTON_NAME = "button";
	private static final String A_SHOW_DECLARATION = "a_show_declaration";
	private static final String A_REGISTRATION = "a_registration";
	
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
		System.out.println(page);
		return page;
	}
}
