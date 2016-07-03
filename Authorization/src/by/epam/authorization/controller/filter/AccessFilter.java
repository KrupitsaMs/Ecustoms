package by.epam.authorization.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.authorization.command.CommandName;
import by.epam.authorization.controller.PageName;
import by.epam.authorization.entity.User;


/**
 * AccessFilter.java
 * Class implements interface Filter
 * It checks user's access to commands.
 * @author MasSword
 */

public class AccessFilter implements Filter{
	private static final String COMMAND_NAME = "command";
	private static final String LOGIN = "login";
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {}
	
	
	/**
     * Method reads a command from the request
     * and checks user's access to selected command.
     * If user doesn't have access to selected command
     * it redirects request to index page
     * @param ServletRequest  request, ServletResponse response, FilterChain chain
     */
	
	@Override
	public void doFilter(ServletRequest  request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String userStatus;
		HttpSession session = ((HttpServletRequest) request).getSession();
		if (null == ((User) session.getAttribute(LOGIN))){
			userStatus = "guest";
			User currentUser = new User();
			currentUser.setRole(userStatus);
			((HttpServletRequest) request).getSession().setAttribute(LOGIN, currentUser);
		} else{
			userStatus =((User) session.getAttribute(LOGIN)).getRole();
		}
        CommandName command = getCommandName((HttpServletRequest) request);
        boolean authorized = checkAccess(userStatus, command);	

        if (authorized) {
            chain.doFilter(request, response);
        } else {
        	((HttpServletResponse) response).sendRedirect(PageName.INDEX_PAGE);
        }
	}

	@Override
	public void destroy() {}

	
	/**
     * Method get status and command as a parameters
     * and measures accessibility of the current command to the user
     * @param String status, CommandName command
     * @return boolean accessibility of the current command
     */
    private boolean checkAccess(String status, CommandName command) {
        UserStatus userStatus = UserStatus.valueOf(status.toUpperCase());
        boolean access = false;
            switch (userStatus) {
            case USER:
                switch (command) {
                    case USER_PAGE:
                        access = true;
                        break;
                    case DECLARATION_CHECK:
                        access = true;
                        break;
                    case LOG_OUT:
                        access = true;
                        break;
                    case USER_NEW_DECLARATION:
                        access = true;
                        break;
                    case CREATE_NEW_DECLARATION:
                        access = true;
                        break;
                    case SUBMIT_NEW_DECLARATION:
                        access = true;
                        break;
                    case ADD_NEW_GOOD:
                        access = true;
                        break;
                    case SHOW_ALL_DECLARATIONS:
                        access = true;
                        break;
                    case DECLARATION_CHOOSE:
                        access = true;
                        break;
                    case CREATE_DECLARATION_CHANGES:
                        access = true;
                        break;
                    case SUBMIT_CHANGING_DECLARATION:
                        access = true;
                        break;
                    case CHANGE_NEW_GOOD:
                        access = true;
                        break;
				default:
					break;
                }
            case ADMIN:
                switch (command) {
                    case A_SHOW_ALL_USERS:
                        access = true;
                        break;
                    case A_USER_REVIEW:
                        access = true;
                        break;
                    case A_USER_CONFIRM:
                        access = true;
                        break;
                    case A_USER_DECLINE:
                        access = true;
                        break;
                    case A_SHOW_DECLARATION:
                        access = true;
                        break;
                    case LOG_OUT:
                        access = true;
                        break;
                    case ADMIN_PAGE:
                        access = true;
                        break;
                    case A_NEW_DECLARATIONS_REVIEW:
                        access = true;
                        break;
                    case A_DECLARATION_CONFIRM:
                        access = true;
                        break;
                    case A_DECLARATION_DECLINE:
                        access = true;
                        break;
                    case A_REGISTRATION:
                        access = true;
                        break;
				default:
					break;
                }
            case GUEST:
            	 switch (command) {
          		case LOGIN:
          		    access = true;
          		    break;
          		case RU:
          		    access = true;
          		    break;
          		case REGISTRATION:
          		    access = true;
          		    break;
          		case EN:
          		    access = true;
          		    break;
          		case RESTORE:
          		    access = true;
          		    break;
          		case EX_DECLARATION_CHECK:
          		    access = true;
          		    break;
				default:
					break;
           }
        }
        return access;
    }
	
	/**
     * Method reads a command from the request
     * and returns CommandName. 
     * @param HttpServletRequest request
     * @return CommandName - object of the CommandName class
     */
    
	 private CommandName getCommandName(HttpServletRequest request) {
	        String commandName;
	        commandName = request.getParameter(COMMAND_NAME);
	        CommandName action;
	        try {
	            action = CommandName.valueOf(commandName.toUpperCase());
	        } catch (IllegalArgumentException ex) {
	            action = CommandName.NO_SUCH_COMMAND;
	        }
	        return action;
	 }
	 
		/**
	     * Internal Enum class
	     * that contains users status in the service
	     */
	    private enum UserStatus {
	        USER, ADMIN, GUEST
	    }
}
