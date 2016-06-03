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

public class ExportDeclarationCheckCommand implements Command{
	private static final String EX_DECL_CHECK = "ex_declaration_number";
	private static final String STATUS = "Confirmed";
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page;
		try {
			DeclService service = ServiceFactory.getServiceFactory().getDeclService(ServiceName.DECLARATION);
			Declaration exDeclaration = service.exDeclarationRequest(request.getParameter(EX_DECL_CHECK));
			if (exDeclaration != null && exDeclaration.getStatus() == STATUS){
				request.setAttribute("declaration", exDeclaration);
				page = PageName.EX_DECLARATION_CONFIRMATION;
			} else if (exDeclaration != null){
				request.setAttribute("declaration", exDeclaration);
				page = PageName.EX_DECLARATION_PROCESSING;
			}
			else{
				exDeclaration = new Declaration(request.getParameter(EX_DECL_CHECK));
				request.setAttribute("declaration", exDeclaration);
				page = PageName.EX_DECLARATION_FAILED;
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return page;
	}

}
