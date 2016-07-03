package by.epam.authorization.command.impl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import by.epam.authorization.command.Command;
import by.epam.authorization.command.exception.CommandException;
import by.epam.authorization.controller.PageName;
import by.epam.authorization.entity.User;
import by.epam.authorization.service.ServiceFactory;
import by.epam.authorization.service.ServiceName;
import by.epam.authorization.service.UserService;
import by.epam.authorization.service.exception.ServiceException;

/**
 * AUserReviewCommand.java
 * Class implemented interface Command
 * Displays user's information on screen with status "not_examined_user"
 * It contains method execute
 * @author MasSword
 */

public class AUserReviewCommand implements Command{
	
	private static final String STATUS = "not yet examined";
	private static final String NOT_EXAMINED_USER = "not_examined_user";
	private static final String LIST_SIZE = "list_size";
	
	
	/**
     * Method reads a command from the request
     * and processes it. The result will be given as
     * a forward page
     * @param request request to read the command from
     * @return forward page - String class object
     */

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		try{
			String page;
			UserService service = ServiceFactory.getServiceFactory().getUserService(ServiceName.LOGIN);
			ArrayList<User> userList = notExaminedUserListCheck(service.userEnquery());
			int listSize = userList.size()-1;
			if (listSize>=0){
				User notExamindeUser = userList.get(0);
				request.getSession().setAttribute(NOT_EXAMINED_USER, notExamindeUser);
				request.getSession().setAttribute(LIST_SIZE, listSize);
				page= PageName.A_USERS_REVIEW;
			} else{
				page = PageName.A_USERS_REVIEW_FAIL;
			}
			return page;	
		} catch (ServiceException e) {
			throw new CommandException(e);
		}

	}
	
	
	// Method handles all the users of the service and returns
	// a list with the status "not_examined_user"
	// @param ArrayList<User> userList - list of all users from services database
	// @return ArrayList<User> - list of users with status "not_examined_user"
	
	private static ArrayList<User> notExaminedUserListCheck(ArrayList<User> userList){
		ArrayList<User> notExaminedUserList = new ArrayList<User>();
		for(int currentUser = 1; currentUser <= userList.size(); currentUser++){
			if (userList.get(currentUser-1).getStatus().equals(STATUS)){
				notExaminedUserList.add(userList.get(currentUser-1));
			}
		}
		
		return notExaminedUserList;
		
	}

}
