package by.epam.authorization.command.impl;

import javax.servlet.http.HttpServletRequest;
import by.epam.authorization.command.Command;
import by.epam.authorization.command.exception.CommandException;
import by.epam.authorization.controller.PageName;
import by.epam.authorization.entity.User;
import by.epam.authorization.service.UserService;
import by.epam.authorization.service.ServiceFactory;
import by.epam.authorization.service.ServiceName;
import by.epam.authorization.service.exception.ServiceException;

public class RegistrationCommand implements Command{
	private static final String LOGIN = "newLogin";
	private static final String PASSWORD = "newPassword";
	private static final String UTN = "newUTN";
	private static final String ORGANIZATION_NAME = "newOrgName";
	private static final String ORGANIZATION_ADDRESS = "newAddres";
	private static final String MAIL = "newMail";
	
	@Override
	public String execute(HttpServletRequest request) throws CommandException{
		String page;
		try {
			UserService service = ServiceFactory.getServiceFactory().getUserService(ServiceName.REGISTRATION);
			User user = service.userEnquery(request.getParameter(LOGIN), request.getParameter(PASSWORD), request.getParameter(UTN),
					request.getParameter(ORGANIZATION_NAME), request.getParameter(ORGANIZATION_ADDRESS), request.getParameter(MAIL));
			if (user != null){	
				page = PageName.REGISTRATION_APPLICATION;
			}else{
				page = PageName.ERROR_REGISTRATION;
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return page;
	}
}
