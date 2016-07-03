package by.epam.authorization.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.authorization.command.Command;
import by.epam.authorization.command.exception.CommandException;
import by.epam.authorization.controller.PageName;
import by.epam.authorization.service.InfService;
import by.epam.authorization.service.ServiceFactory;
import by.epam.authorization.service.ServiceName;
import by.epam.authorization.service.exception.ServiceException;

/**
 * RestoreCommand.java
 * Class implemented interface Command
 * This class provides interface to restore password of user's account
 * It contains method execute
 * @author MasSword
 */

public class RestoreCommand implements Command{
	private static final String MAIL = "recMail";
	
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
		try{
			InfService service = ServiceFactory.getServiceFactory().getInfService(ServiceName.RESTORE);
			String mailConfirmation = service.mailRequest(request.getParameter(MAIL));
			if (mailConfirmation != null){	
				page = PageName.RESTORE_SUCCESS;
			}else{
				page = PageName.ERROR_RESTORE;
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}		
	    return page;
	}
}
