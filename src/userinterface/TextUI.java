/**
 * 
 */
package userinterface;

import java.io.*;

/**
 * @author Bobby Dillingham
 *
 */
public class TextUI {
	

	private static final int CONSOLESIZE = 80;
	
	private InputStream in;
	private PrintStream out;
	
	public TextUI(InputStream in, PrintStream out) {
		this.in=in;
		this.out=out;
		System.console().
	}

	public void printFullLine(char c) {
		for(int i = 0;i<TextUI.CONSOLESIZE;i++){
			out.print(c);
		}
		out.println();
	}

	public void wrappedPrint(char c, Object object) {
		// TODO Auto-generated method stub
		
	}

}
