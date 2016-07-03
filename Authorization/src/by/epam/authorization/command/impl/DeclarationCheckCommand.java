package by.epam.authorization.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.authorization.command.Command;
import by.epam.authorization.command.exception.CommandException;
import by.epam.authorization.controller.PageName;
import by.epam.authorization.entity.Declaration;
import by.epam.authorization.entity.User;
import by.epam.authorization.service.DeclService;
import by.epam.authorization.service.ServiceFactory;
import by.epam.authorization.service.ServiceName;
import by.epam.authorization.service.exception.ServiceException;

/**
 * DeclarationCheckCommand.java
 * Class implemented interface Command
 * This class gives opportunity to check status of any user's declaration of service
 * It contains method execute
 * @author MasSword
 */

public class DeclarationCheckCommand implements Command{
	private static final String DECL_CHECK = "declaration_number";
	private static final String STATUS = "confirmed";
	private static final String REJECTED_STATUS = "rejected";
	private static final String IM_TYPE = "IM";
	private static final String EX_TYPE = "EX";
	
	
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
		try {
			DeclService service = ServiceFactory.getServiceFactory().getDeclService(ServiceName.DECLARATION);
			Declaration declaration = service.declarationRequest(request.getParameter(DECL_CHECK));
			if (utnCheck(request, declaration)){
				page = choicePage(request, declaration);
			} else{
				declaration = new Declaration(request.getParameter(DECL_CHECK));
				request.setAttribute("declaration", declaration);
				page = PageName.DECLARATION_FAILED;
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return page;
	}
	

	// Method compares declaration's UTN requested by the user with the user UTN
	// @param HttpServletRequest request, Declaration declaration
	// @return boolean
	
	private static boolean utnCheck(HttpServletRequest request, Declaration declaration) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("login");
		String userUTN = user.getUTN();
		if (declaration != null){
			if (userUTN.equals(declaration.getUTN())){
			    return true;
			} else{
				return false;
			}
		} else {
			return false;
		}
	}
	
	// Method analyzes the status and type of the requested declaration and defines forward page
	// @param HttpServletRequest request, Declaration declaration
	// @return forward page - String class object
	
	private static String choicePage(HttpServletRequest request, Declaration declaration){
		String page;
		if (declaration.getStatus().equals(STATUS) &&  declaration.getType().equals(IM_TYPE)){
			request.setAttribute("declaration", declaration);
			page = PageName.DECLARATION_CONFIRMATION;
			return page;
		} 
		if (declaration.getStatus().equals(STATUS) &&  declaration.getType().equals(EX_TYPE)){
			request.setAttribute("declaration", declaration);
			page = PageName.U_EX_DECLARATION_CONFIRMATION;
			return page;
		}
		if (declaration.getStatus().equals(REJECTED_STATUS)){
			request.setAttribute("declaration", declaration);
			page = PageName.DECLARATION_REJECT;
			return page;
		}
		else {
			request.setAttribute("declaration", declaration);
			page = PageName.DECLARATION_PROCESSING;
			return page;
		}
	}
}
