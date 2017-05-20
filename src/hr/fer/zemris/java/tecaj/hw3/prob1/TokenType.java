package hr.fer.zemris.java.tecaj.hw3.prob1;

/**
 * Types of token.
 * @author Mislav Gillinger
 * @version 1.0
 */
public enum TokenType {
	/** Token type which represents an end of file. */
	EOF, 
	/** Token type which represents a word. */
	WORD,
	/** Token type which represents a number. */
	NUMBER,
	 /** Token type which represents a symbol. */
	SYMBOL;
}
