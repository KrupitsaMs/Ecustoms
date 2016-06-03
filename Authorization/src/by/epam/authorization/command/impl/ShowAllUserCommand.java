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

public class ShowAllUserCommand implements Command{
	
	private static final String USER_LIST="user_list";

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
