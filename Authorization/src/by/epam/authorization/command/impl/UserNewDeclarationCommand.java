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
 * UserNewDeclarationCommand.java
 * Class implemented interface Command
 * Defines new number to submitted declaration
 * It contains method execute
 * @author MasSword
 */

public class UserNewDeclarationCommand implements Command{
	
	private static final String NUMBER = "declaration_number";
	
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
			InfService service = ServiceFactory.getServiceFactory().getInfService(ServiceName.MAX_DECL_NUMBER);
			String maxDeclNumber = service.maxDeclarationNumberRequest();
			String newDeclNumber = declNumberCalculation(maxDeclNumber);
			request.getSession().setAttribute(NUMBER, newDeclNumber);
			page = PageName.ADD_NEW_DECLARATION;
		} catch (ServiceException e) {
			throw new CommandException(e);
		}		
	    return page;
	}
	
	private static String declNumberCalculation (String maxDeclNumber){
		int number = Integer.parseInt(maxDeclNumber) + 1;
		return maxDeclNumber = Integer.toString(number);
	}

}
