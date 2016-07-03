package by.epam.authorization.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.authorization.command.exception.CommandException;

/**
 * Command.java
 * Interface class which becomes a common Interface for all JSP's commands
 * It contains method execute
 * @author MasSword
 */

public interface Command {
	
	/**
     * Method reads a command from the request
     * and processes it. The result will be given as
     * a forward page
     *
     * @param request request to read the command from
     * @return forward page - String class object
     */
	
	String execute(HttpServletRequest request) throws CommandException;
}
