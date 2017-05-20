package hr.fer.zemris.java.custom.collections;

/**
 * Exception thrown when an element from stack is being reached, and the stack
 * is empty.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class EmptyStackException extends RuntimeException{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor for class EmptyStackException.
	 */
	public EmptyStackException(){
		
	}
	
	/**
	 * Constructor which calls a superior constructor with String message.
	 * @param message Message to be sent to superior constructor.
	 */
	public EmptyStackException(String message){
		super(message);
	}
	
	/**
	 * Constructor which calls a superior constructor with Throwable cause.
	 * @param cause Cause to be sent to superior constructor.
	 */
	public EmptyStackException(Throwable cause){
		super(cause);
	}
	
	/**
	 * Constructor which calls a superior constructor with String message and 
	 * Throwable cause.
	 * @param message Message to be sent to superior constructor.
	 * @param cause Cause to be sent to superior constructor.
	 */
	public EmptyStackException(String message, Throwable cause){
		super(message, cause);
	}
}
