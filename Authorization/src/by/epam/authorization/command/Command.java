package by.epam.authorization.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.authorization.command.exception.CommandException;

public interface Command {
	String execute(HttpServletRequest request) throws CommandException;
}
