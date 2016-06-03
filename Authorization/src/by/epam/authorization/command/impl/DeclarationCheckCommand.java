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

public class DeclarationCheckCommand implements Command{
	private static final String DECL_CHECK = "declaration_number";
	private static final String STATUS = "Confirmed";
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
	
	private static String choicePage(HttpServletRequest request, Declaration declaration){
		String page;
		if (declaration.getStatus() == STATUS){
			request.setAttribute("declaration", declaration);
			page = PageName.DECLARATION_CONFIRMATION;
			return page;
		} else {
			request.setAttribute("declaration", declaration);
			page = PageName.DECLARATION_PROCESSING;
			return page;
		}
	}
}
