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

public class SubmitNewDeclarationCommand implements Command{
	private static final String NEW_DECLARATION = "new_declaration";
	private static final String NEW_DECLARATION_NUMBER = "new_declaration_number";
	@Override
	public String execute(HttpServletRequest request) throws CommandException{
		String page;
		try {
			Declaration newDeclaration = (Declaration) request.getSession().getAttribute(NEW_DECLARATION);
			DeclService service = ServiceFactory.getServiceFactory().getDeclService(ServiceName.DECLARATION_SUBMISSION);
			String newDeclarationNumber = service.declarationSubmission(newDeclaration);
			if (newDeclarationNumber != null){
				request.getSession().setAttribute(NEW_DECLARATION_NUMBER, newDeclarationNumber);	
				page = PageName.DECLARATION_SUB_SUCCESSFULL;
			}else{
				page = PageName.DECLARATION_SUB_FAILED;
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return page;
	}
}
