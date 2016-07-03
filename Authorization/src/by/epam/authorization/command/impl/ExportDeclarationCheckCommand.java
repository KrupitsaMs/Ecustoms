package by.epam.authorization.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.authorization.command.Command;
import by.epam.authorization.command.exception.CommandException;
import by.epam.authorization.controller.PageName;
import by.epam.authorization.entity.Declaration;
import by.epam.authorization.service.DeclService;
import by.epam.authorization.service.ServiceFactory;
import by.epam.authorization.service.ServiceName;
import by.epam.authorization.service.exception.ServiceException;

/**
 * ExportDeclarationCheckCommand.java
 * Class implemented interface Command
 * This class gives opportunity to check status of any export declaration of service with the status of guest
 * It contains method execute
 * @author MasSword
 */

public class ExportDeclarationCheckCommand implements Command{
	private static final String EX_DECL_CHECK = "ex_declaration_number";
	private static final String STATUS = "confirmed";
	private static final String STATUS_REJ = "rejected";
	private static final String STATUS_NOT = "not yet examined";
	
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
		try {
			DeclService service = ServiceFactory.getServiceFactory().getDeclService(ServiceName.DECLARATION);
			Declaration exDeclaration = service.exDeclarationRequest(request.getParameter(EX_DECL_CHECK));
			if (exDeclaration == null){
				exDeclaration = new Declaration(request.getParameter(EX_DECL_CHECK));
				request.setAttribute("declaration", exDeclaration);
				page = PageName.EX_DECLARATION_FAILED;
			} else{
				String currentStatus = exDeclaration.getStatus();
				switch (currentStatus) {
				case STATUS:
					request.setAttribute("declaration", exDeclaration);
					page = PageName.EX_DECLARATION_CONFIRMATION;
					break;
				case STATUS_NOT:
					request.setAttribute("declaration", exDeclaration);
					page = PageName.EX_DECLARATION_REJ;
					break;		
				case STATUS_REJ:
					request.setAttribute("declaration", exDeclaration);
					request.setAttribute("declaration", exDeclaration);
					page = PageName.EX_DECLARATION_PROCESSING;
					break;
				default:
					break;
				}
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return page;
	}

}
