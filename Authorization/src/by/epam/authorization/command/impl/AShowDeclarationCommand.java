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

public class AShowDeclarationCommand implements Command{
	
	private static final String DECL_CHECK = "decl_number";
	private static final String SEARCHED_DECLARATION = "searched_declaration";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page;
		try {
			DeclService service = ServiceFactory.getServiceFactory().getDeclService(ServiceName.DECLARATION);
			Declaration declaration = service.adminDeclarationRequest(request.getParameter(DECL_CHECK));
			if (declaration==null){
				page = PageName.DECLARATION_FAILED;
			} else{
				request.setAttribute(SEARCHED_DECLARATION, declaration);
				page = PageName.A_DECLARATION_SHOW_SUCC;
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return page;
	}

}
