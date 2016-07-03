package by.epam.authorization.dao.conpool.exception;

/**
 * ConnectionPoolException.java
 * Exception class it's a common exception for objects of ConnectionPoolException.class
 * @author MasSword
*/

public class ConnectionPoolException extends Exception{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new exception with the specified detail message and ex.
	 * This constructor is useful for exceptions that becomes wrappers for other Exceptions.
	 * @param message - String class object
     * @param e - Exception class object
     */
	
	 public ConnectionPoolException(String message, Exception ex) {
	        super (message, ex);
	 }

		/**
		 * Constructs a new exception with the specified detail message.
	     * @param message - String class object
	     */
	 
	public ConnectionPoolException(String message) {}
}
