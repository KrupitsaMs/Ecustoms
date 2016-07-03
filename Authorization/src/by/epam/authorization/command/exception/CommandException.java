package by.epam.authorization.command.exception;

/**
 * CommandException.java
 * Exception class it's a common exception for objects of Command.java
 * @author MasSword
*/

public class CommandException extends Exception{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs a new exception with the specified detail message.
     * @param message - String class object
     */
	
	public CommandException(String message){
		super(message);
	}
	
	/**
	 * Constructs a new exception with the specified e.
	 * This constructor is useful for exceptions that becomes wrappers for other Exceptions.
     * @param e - Exception class object
     */
	
	public CommandException(Exception e){
		super(e);
	}
	
	/**
	 * Constructs a new exception with the specified detail message and e.
	 * This constructor is useful for exceptions that becomes wrappers for other Exceptions.
	 * @param message - String class object
     * @param e - Exception class object
     */

	public CommandException(String message, Exception e){
		super(message, e);
	}
}
