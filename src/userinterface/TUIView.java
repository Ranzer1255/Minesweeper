package userinterface;

import java.io.InputStream;
import java.io.PrintStream;

import minesweeper.Field;
import minesweeper.Minesweeper;

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
	
	public boolean playAgain() {
		boolean rtn = false;
		boolean illegalArgument = true;
		do {
			try {
				ui.centerPrint("Play again?");
				String input = ui.inputPrompt("(y,n): ");
				if(input.toLowerCase().charAt(0) == 'y'){
					rtn = true;
				} else if (input.toLowerCase().charAt(0) == 'n'){
					rtn = false;
				} else {
					throw new IllegalArgumentException();
				}
			} catch (IllegalArgumentException e) {
				ui.centerPrint("I'm sorry, I didn't understand that. Please try again.");
			} 
		} while (illegalArgument);
		return rtn;
	}

	public int getX(int maxsize) {
		// TODO Auto-generated method stub
		return ui.nextBountedInt("How wide?", 1,maxsize);
	}
	
	public int getY(int maxsize){
		return ui.nextBountedInt("How tall?", 1,maxsize);
	}
	
	public int getMines(int maxsize){
		return ui.nextBountedInt("How Many Mines?", 1,maxsize);
	}

	public void print(StringBuilder grid) {

		ui.print(grid);
		
	}
}
