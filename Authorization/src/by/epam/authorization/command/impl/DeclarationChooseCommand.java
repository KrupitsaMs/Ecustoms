package by.epam.authorization.command.impl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import by.epam.authorization.command.Command;
import by.epam.authorization.command.exception.CommandException;
import by.epam.authorization.controller.PageName;
import by.epam.authorization.entity.Declaration;
import by.epam.authorization.entity.User;
import by.epam.authorization.service.DeclService;
import by.epam.authorization.service.ServiceFactory;
import by.epam.authorization.service.ServiceName;
import by.epam.authorization.service.exception.ServiceException;

public class DeclarationChooseCommand implements Command{
	private static final String LOGIN = "login";
	private static final String DECL_NUMBER = "declaration_number";
	private static final String SEARCHED_DECL = "searched_decl";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page;
		try {
			DeclService service = ServiceFactory.getServiceFactory().getDeclService(ServiceName.DECLARATION);
			User user = (User) request.getSession().getAttribute(LOGIN);
			String utn = user.getUTN();
			ArrayList <Declaration> declList = service.userDeclarationListRequest(utn);
			String searchedDeclNumber = request.getParameter(DECL_NUMBER);
			Declaration searchedDecl = null;
			if (declList.isEmpty()){
				page = PageName.ALL_USER_DECLARATION_FAILED;
			}
			for (int curDeclNumber = 1; curDeclNumber <=declList.size(); curDeclNumber++){
				if (searchedDeclNumber.equals(declList.get(curDeclNumber-1).getNumber())){
					searchedDecl = declList.get(curDeclNumber-1);
					break;
					}
			}
			if (searchedDecl == null){
				page = PageName.DECLARATION_CHANGE_NUMBER_FAILED;
			} else {
				request.getSession().setAttribute(SEARCHED_DECL, searchedDecl);
				page = PageName.DECLARATION_CHANGINGS;
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return page;
	}

}
