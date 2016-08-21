package userinterface;

import java.io.InputStream;
import java.io.PrintStream;

public class TUIView {

	private TUIHelper ui;
	
	public TUIView(InputStream in, PrintStream out){
		
		ui=new TUIHelper(in, out);
	}
	
	public void displayWelcome() {
		
		ui.printFullLine('*');
		ui.wrappedPrint('|',"");
		ui.wrappedPrint('|', "Welcome to Minesweeper for Command Line!");
		ui.wrappedPrint('|', "By");
		ui.wrappedPrint('|', "Bobby Dillingham");
		ui.wrappedPrint('|', "");
		ui.printFullLine('*');
	}
	
	public void displayGoodbye() {
		ui.printFullLine('*');
		ui.wrappedPrint('*', "");
		ui.wrappedPrint('*', "Thanks");
		ui.wrappedPrint('*', "for playing");
		ui.wrappedPrint('*', "Minesweeper for Command Line!");
		ui.wrappedPrint('*', "");
		ui.printFullLine('*');
	}
	
	
}
