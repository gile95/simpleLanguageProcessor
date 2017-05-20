package hr.fer.zemris.java.tecaj.hw3.prob1;

/**
 * This is a subsystem of a simple language processor which task is lexical analysis.
 * The input of this subsystem is a source text of a program or a document,
 * and the output is a series of tokens.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class Lexer {
	/** Text to be analyzed. */
	private char[] data;
	/** Meaningful part of text. */
	private Token token;
	/** Current index in char array data. */
	private int currentIndex;
	/** One of states of the lexer. */
	private LexerState state;
	
	/**
	 * Constructor which accepts a text to be analyzed.
	 * @param text string which will be analyzed and separated into tokens.
	 */
	public Lexer(String text){
		if(text == null){
			throw new IllegalArgumentException();
		}
		
		data = text.trim().toCharArray();
		token = new Token();
		currentIndex = 0;
		state = LexerState.BASIC;
	}
	
	/**
	 * Analyzed text and searches for the next token.
	 * @return first token found after position currentIndex.
	 */
	public Token nextToken(){
		int dataLength = data.length;
		
		if(token != null){
			if(token.type != null){
				if(getToken().type.equals(TokenType.EOF)){
					throw new LexerException();
				}
			}
		}
		
		if(state == LexerState.BASIC){
			while(currentIndex < dataLength){
				while(data[currentIndex] == '\r' || data[currentIndex] == '\n'
						|| data[currentIndex] == '\t' || data[currentIndex] == ' '){
					currentIndex++;
				} 
				
				if((data[currentIndex] == '\\') && (currentIndex == dataLength-1)){
					throw new LexerException();
				}
				
				if (Character.isLetter(data[currentIndex]) 
						|| (data[currentIndex] == '\\'
						&& (Character.isDigit(data[currentIndex+1])
							|| data[currentIndex+1] == '\\')
						)
					){
					String retString;
					
					if(Character.isLetter(data[currentIndex])){
						retString = String.valueOf(data[currentIndex++]);
					}
					else{
						retString = String.valueOf(data[++currentIndex]);
						if(currentIndex == dataLength-1){
							currentIndex++;
							token.type = TokenType.WORD;
							token.value = retString;
							return token;
						}
						else{
							currentIndex++;
						}
					}
					
					while (Character.isLetter(data[currentIndex]) 
							|| (data[currentIndex] == '\\'
								&& (Character.isDigit(data[currentIndex+1])
									|| data[currentIndex+1] == '\\')
								)
							){
						if((data[currentIndex] == '\\'
								&& (Character.isDigit(data[currentIndex+1])
										|| data[currentIndex+1] == '\\')
									)){
							currentIndex++;
							retString = retString.concat(String.valueOf(data[currentIndex++]));
						}
						else{
							retString = retString.concat(String.valueOf(data[currentIndex++]));
						}
						if(currentIndex == dataLength) break;
					}
					token.type = TokenType.WORD;
					token.value = retString;
					return token;
				}
				
				else if(Character.isDigit(data[currentIndex])){
					String retString = String.valueOf(data[currentIndex++]);
					while(Character.isDigit(data[currentIndex])){
						retString = retString.concat(String.valueOf(data[currentIndex++]));
						if(currentIndex == dataLength) break;
					}
					
					token.type = TokenType.NUMBER;
					try{
						token.value = Long.parseLong(retString);
					}catch (NumberFormatException e){
						throw new LexerException();
					}
					return token;
				}
				
				else if((data[currentIndex] == '\\'
						&& (!Character.isDigit(data[currentIndex+1])
								&& data[currentIndex+1] != '\\')
							)){
					throw new LexerException();
				}
				
				else{
					token.type = TokenType.SYMBOL;
					token.value = data[currentIndex++];
					return token;
				}
			}
		}
		
		else{
			while(currentIndex < dataLength){
				if(data[currentIndex] == '#'){
					token.type = TokenType.SYMBOL;
					token.value = data[currentIndex++];
					return token;
				}
				
				else{
					if(data[currentIndex] == ' ') currentIndex++;
					String retString = String.valueOf(data[currentIndex++]);
					
					while(data[currentIndex] != ' ' && data[currentIndex] != '#'){
						retString = retString.concat(String.valueOf(data[currentIndex++]));
						if(currentIndex == dataLength) break;
					}
					
					token.type = TokenType.WORD;
					token.value = retString;
					return token;
				}
			}
		}
		
		token.type = TokenType.EOF;
		token.value = null;
		return token;
	}
	
	/**
	 * Returns last token found.
	 * @return last token found
	 */
	public Token getToken(){
		return token;
	}
	
	/**
	 * Setter for state in which a lexer works.
	 * @param state One of two states in which a lexer is able to work. Can be BASIC or EXTENDED
	 */
	public void setState(LexerState state){
		if(state == null){
			throw new IllegalArgumentException();
		}
		this.state = state;
	}
}
