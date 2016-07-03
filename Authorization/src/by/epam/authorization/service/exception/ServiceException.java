package by.epam.authorization.service.exception;

/**
 * ServiceException.java
 * Exception class it's a common exception for objects of Service.java
 * @author MasSword
*/

public class ServiceException extends Exception{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new exception with the specified detail message.
     * @param message - String class object
     */
	
	public ServiceException(String message){
		super(message);
	}
	
	/**
	 * Constructs a new exception with the specified detail message and e.
	 * This constructor is useful for exceptions that becomes wrappers for other Exceptions.
	 * @param message - String class object
     * @param e - Exception class object
     */
	
	public ServiceException(String message, Exception e){
		super(message, e);
	}
}
