package by.epam.authorization.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.epam.authorization.command.Command;
import by.epam.authorization.command.exception.CommandException;
import by.epam.authorization.controller.helper.CommandHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Controller.java
 * Class extended HttpServlet.class
 * Provides handling of user's http requests and preparing http responses
 * @author MasSword
 */

public class Controller extends HttpServlet{
	private static final Logger LOGGER = LogManager.getLogger(Controller.class);
	private static final long serialVersionUID = 1L;
	private static final String COMMAND_NAME = "command";
	private final CommandHelper commandHelper = CommandHelper.getCommandHelperInstance();
	
	public Controller() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		processRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		processRequest(request, response);
	}
	
	/**
     * Method handles http data from the request
     * processes it and send to the user http response
     * @param HttpServletRequest request
     * @param HttpServletResponse response
     */
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		String commandName = null;
		Command command = null;
		String page = null;
		try{
			commandName = request.getParameter(COMMAND_NAME);
			System.out.println(commandName);
			command = commandHelper.getCommand(commandName);
			page = command.execute(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}catch(ServletException | IOException | CommandException e){
			LOGGER.error(e);
			page = PageName.ERROR_PAGE;
		}
	}
}