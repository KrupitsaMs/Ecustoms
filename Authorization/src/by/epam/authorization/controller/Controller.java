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

public class Controller extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final String COMMAND_NAME = "command";
	private final CommandHelper commandHelper = CommandHelper.getCommandHelperInstance();
	
	public Controller() {
        super();
    }
	
	@Override
    protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
    	super.service(arg0, arg1);
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName = null;
		Command command = null;
		String page = null;
		try{
			commandName = request.getParameter(COMMAND_NAME);
			System.out.println(commandName);
			command = commandHelper.getCommand(commandName);
			page = command.execute(request);			
		}catch(CommandException e){
			Logger LOG = LogManager.getLogger(HttpServlet.class.getName());
			LOG.error("Some truble in code", e);
			page = PageName.ERROR_PAGE;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		if (dispatcher != null){
			dispatcher.forward(request, response);
		}else{
			// to do
		}
	}
}