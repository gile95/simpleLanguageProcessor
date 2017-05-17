package hr.fer.zemris.java.tecaj.hw3.prob1;

/**
 * Exception that handles all exceptions that occur during analysis. 
 * @author Mislav Gillinger
 * @version 1.0
 */

public class LexerException extends RuntimeException{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor for class LexerException
	 */
	public LexerException(){
		
	}
	
	/**
	 * Constructor which accepts a message and delegates it to a superior exception.
	 * @param message - message sent from where an exception occured
	 */
	public LexerException(String message){
		super(message);
	}
}
