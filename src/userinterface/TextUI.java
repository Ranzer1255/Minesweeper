/**
 * 
 */
package userinterface;

import java.io.*;

/**
 * Wrapper Class for formated Input and Output to a console
 * @author Bobby Dillingham
 *
 */
public class TextUI {
	

	private final int CONSOLESIZE;
	
	private InputStream in;
	private PrintStream out;
	
	
	/**
	 * basic constructor that will default to System.in and System.out with a default CONSOLESIZE of 80
	 */
	public TextUI() {
		this.in=System.in;
		this.out=System.out;
		this.CONSOLESIZE=80;
	}	
	
	public TextUI(InputStream in, PrintStream out) {
		this.in=in;
		this.out=out;
		this.CONSOLESIZE=80;
	}
	
	public TextUI(InputStream in, PrintStream out, int size) {
		this.in=in;
		this.out=out;
		this.CONSOLESIZE=size;
	}

	public void printFullLine(char c) {
		for(int i = 0;i<CONSOLESIZE;i++){
			out.print(c);
		}
		out.println();
	}

	public void wrappedPrint(char c, String text) {

		String print = "";
		int whitespace = CONSOLESIZE/2-text.length()/2-1;
		
		print += c;
		print += addWhitespace(whitespace);
		print += text;
		print += addWhitespace(whitespace);
		print += c;
		
		out.println(print);
	}
	
	private String addWhitespace(int whitespace){
		String rtn="";
		for(int i=0;i<whitespace;i++){
			rtn += " ";
		}
		return rtn;
	}

	public void centerPrint(String string) {

		String print = "";
		int whitespace = CONSOLESIZE/2-string.length()/2;
		
		print += addWhitespace(whitespace);
		print +=string;
		
		out.println(print);
		
	}

}
