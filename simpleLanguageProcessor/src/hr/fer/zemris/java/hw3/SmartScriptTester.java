package hr.fer.zemris.java.hw3;

import hr.fer.zemris.java.custom.scripting.nodes.DocumentNode;
import hr.fer.zemris.java.custom.scripting.nodes.ForLoopNode;
import hr.fer.zemris.java.custom.scripting.nodes.Node;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParser;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParserException;

import java.nio.file.Files;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

/**
 * Class which represents SmartScriptParser functionalities
 * @author Mislav Gillinger
 * @version 1.0
 */
public class SmartScriptTester {

	/**
	 * Program execution starts with this method.
	 * @param args Command line arguments
	 * @throws IOException  if an I/O error occurs reading from the stream
	 */
	public static void main(String[] args) throws IOException {
		
		String docBody = new String(
				Files.readAllBytes(Paths.get(args[0])),
				StandardCharsets.UTF_8
				);
	
		SmartScriptParser parser = null;
		try {
			parser = new SmartScriptParser(docBody);
		} catch (SmartScriptParserException e) {
			System.out.println("Unable to parse document!");
			System.exit(-1);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("If this line ever executes, you have failed this class!");
			System.exit(-1);
		}
		
		DocumentNode document = parser.getDocumentNode();
		String originalDocumentBody = createOriginalDocumentBody(document);
		System.out.println(originalDocumentBody);
		
		System.out.println();
		System.out.println("_________________________");
		System.out.println();
		
		SmartScriptParser parser2 = new SmartScriptParser(originalDocumentBody);
		DocumentNode document2 = parser2.getDocumentNode();
		String copyDocumentBody = createOriginalDocumentBody(document2);
		System.out.println(copyDocumentBody);
	}
	
	/**
	 * Creates a String representation of stored document body.
	 * @param document A node which represents a root of a whole body
	 * @return String representation of tokens
	 */
	private static String createOriginalDocumentBody(Node document){
		return rec(document, "");
	}

	/**
	 * Creates a String representation of all tokens
	 * @param document A node which represents a root
	 * @param string Empty string on which other strings will be added
	 * @return String representation of all tokens
	 */
	private static String rec(Node document, String string) {
		string = document.toString();
		if(document instanceof DocumentNode || document instanceof ForLoopNode){
			for(int i = 0; i < document.numberOfChildren(); i++){
				string = string + rec(document.getChild(i), string);
			}	
			if(document instanceof ForLoopNode) {
				string += "{$END$}";
			}
		}
		return string;
	}
}
