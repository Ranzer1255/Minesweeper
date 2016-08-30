package textInterface;

import java.io.InputStream;
import java.io.PrintStream;

import minesweeper.Location;

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
	
	public String playAgain() {
		ui.centerPrint("Play again?");
		return ui.inputPrompt("(y,n): ");
	}

	public int getRow(int maxsize) {
		// TODO Auto-generated method stub
		return ui.nextBountedInt("How tall?", 1,maxsize);
	}
	
	public int getCol(int maxsize){
		return ui.nextBountedInt("How wide?", 1,maxsize);
	}
	
	public int getMines(int maxsize){
		return ui.nextBountedInt("How Many Mines?", 1,maxsize);
	}

	public void print(StringBuilder grid) {

		ui.print(grid);
		
	}

	public void displayLoseGameOver() {

		ui.centerPrint("BOOM! YOU LOSE!");
		
	}

	public void displayWinGameOver() {

		ui.centerPrint("Phew! that was too close, Congrats!");
	}

	public String nextCommand() {

		ui.centerPrint("what would you like to do?");
		ui.centerPrint("1) Click a Spot");
		ui.centerPrint("2) Flag a Spot");
		ui.centerPrint("3) Quit the Game");
		
		return ui.inputPrompt("Your move: ");
		
	}

	public void badInput(String string) {

		ui.centerPrint("I'm sorry i didn't uderstand: " + string);
	}

	public Location getLoc() {
		return new Location(getX(), getY());
	}

	private int getY() {
		int rtn = 0;
		
		String in = ui.inputPrompt("Row: ");
		rtn = parseInput(in);
		
		return rtn;
		
	}

	private int getX() {
	int rtn = 0;
		
		String in = ui.inputPrompt("Col: ");
		
		rtn = parseInput(in);
		
		return rtn;
	
	}

	private int parseInput(String in) {
		int rtn = in.toLowerCase().charAt(0)-'a';
		if (rtn<0) throw new NumberFormatException("Please enter a valid letter");;
		return rtn;
	}
}
