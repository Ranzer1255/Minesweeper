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

	public void wrappedPrint(char c, Object object) {
		// TODO Auto-generated method stub
		
	}

}
