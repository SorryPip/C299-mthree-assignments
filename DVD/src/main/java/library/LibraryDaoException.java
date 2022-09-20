package library;

/**
 * This is the error class for this app, and extends Exception
 * 
 * @author Yu
 *
 */
public class LibraryDaoException extends Exception {
	public LibraryDaoException(String message) {
		super(message);
	}
	
	public LibraryDaoException(String message, Throwable cause) {
		super(message, cause);
	} 
}
