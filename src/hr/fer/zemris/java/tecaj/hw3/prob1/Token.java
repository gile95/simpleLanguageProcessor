package hr.fer.zemris.java.tecaj.hw3.prob1;

/**
 * This class represents a token, which is a meaningful part of a text.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class Token {
	/** Type of a token. */
	protected TokenType type;
	/** Information that token carries. */
	protected Object value;
	
	/**
	 * Default constructor for class Token.
	 */
	public Token(){
		
	}
	
	/**
	 * Constructor which accepts token type and token value.
	 * @param type - type of a token.
	 * @param value - information that token carries.
	 */
	public Token (TokenType type, Object value){
		this.type = type;
		this.value = value;
	}
		
	/**
	 * Getter for token's value.
	 * @return token's value.
	 */
	public Object getValue(){
		return value;
	}
		
	/**
	 * Getter for token's type.
	 * @return token's type
	 */
	public TokenType getType(){
		return type;
	}
}
