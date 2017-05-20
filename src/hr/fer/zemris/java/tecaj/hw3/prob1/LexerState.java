package hr.fer.zemris.java.tecaj.hw3.prob1;

/**
 * States of lexer.
 * @author Mislav Gillinger
 * @version 1.0
 */
public enum LexerState {
	/** State of lexer in which it recognizes words, numbers and symbols. */
	BASIC, 
	/** State of lexer in which it recognizes words, numbers and symbols, until the first occurrence of symbol #.
	 * Everything after that symbol will be treated like a word, until the next occurrence of the symbol #, when
	 * the lexer switches to basic state again. */
	EXTENDED;
}
