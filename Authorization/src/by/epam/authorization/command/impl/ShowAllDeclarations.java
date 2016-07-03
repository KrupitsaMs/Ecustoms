package by.epam.authorization.command.impl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import by.epam.authorization.command.Command;
import by.epam.authorization.command.exception.CommandException;
import by.epam.authorization.controller.PageName;
import by.epam.authorization.entity.Declaration;
import by.epam.authorization.entity.Good;
import by.epam.authorization.entity.User;
import by.epam.authorization.service.DeclService;
import by.epam.authorization.service.ServiceFactory;
import by.epam.authorization.service.ServiceName;
import by.epam.authorization.service.exception.ServiceException;

/**
 * ShowAllDeclarations.java
 * Class implemented interface Command
 * This class shows all user's declarations 
 * It contains method execute
 * @author MasSword
 */

public class ShowAllDeclarations implements Command{
	private static final String LOGIN = "login";
	private static final String DECL_LIST = "declaration_list";

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
			User user = (User) request.getSession().getAttribute(LOGIN);
			String utn = user.getUTN();
			ArrayList <Declaration> declList = service.userDeclarationListRequest(utn);
			if (declList.isEmpty()){
				page = PageName.ALL_USER_DECLARATION_FAILED;
			} else{
				declList = briningList(declList);
				request.setAttribute(DECL_LIST, declList);
				page = PageName.ALL_USER_DECLARATION;
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return page;
	}
	

    // Method handles the all Declarations list 
	// and return declarations submitted by the user 
	// a forward page
	// @param ArrayList <Declaration> declList
	// @return ArrayList <Declaration> 
	
	private static ArrayList <Declaration> briningList( ArrayList <Declaration> declList) {
		ArrayList <Declaration> newDeclList = new ArrayList <Declaration>();
		for (int declNumber = 0; declNumber < declList.size(); declNumber++){
			Declaration oldDeclaration = declList.get(declNumber);
			for (int goodNumber = 1; goodNumber<=oldDeclaration.getDeclarationGoods().size(); goodNumber++){
				Declaration newDeclaration = new Declaration();
				newDeclaration.setNumber(oldDeclaration.getNumber());
				newDeclaration.setTrade_country(oldDeclaration.getTrade_country());
				newDeclaration.setType(oldDeclaration.getType());
				ArrayList<Good> newGoodList = new ArrayList<Good>();
				newGoodList.add(oldDeclaration.getDeclarationGoods().get(goodNumber-1));
				newDeclaration.setDeclarationGoods(newGoodList);
				System.out.println(newDeclaration.getDeclarationGoods().get(0).getName());
				newDeclList.add(newDeclaration);
			}
		}
		return newDeclList;
	}

}
