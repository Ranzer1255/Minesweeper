package main;
import minesweeper.*;
import userinterface.*;

public class Game {
	
	private static boolean gameOver=false;
	private static boolean playing=true;
	private static Minesweeper game;
	private static TextUI ui;
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		game  = new Minesweeper();
		ui = new TextUI(System.in, System.out);
		
		displayWelcome();

		while (playing){
			
			getSettings();
			
			while(!gameOver){
				
				displayGrid();
				try{
					makeMove();
				}catch(MinesweeperException e){
					String msg =  e.getMessage();
					if (msg == "mine"){
						displayGameOver(Minesweeper.LOSE);
						playAgain();
					}else if (msg == "win"){
						displayGameOver(Minesweeper.WIN);
						playAgain();
					}else if (msg == "quit"){
						displayGoodbye();
						playing = false;
						gameOver=true;
					}
				}
								
				
			}
			
		}
		
		
	}
	
	private static void displayWelcome() {
		
		ui.printFullLine('*');
		ui.wrappedPrint('|',"");
		ui.wrappedPrint('|', "Welcome to Minesweeper for Command Line!");
		ui.wrappedPrint('|', "By");
		ui.wrappedPrint('|', "Bobby Dillingham");
		ui.wrappedPrint('|', "");
		ui.printFullLine('*');
		
		
		
		
	}
	
	private static void displayGoodbye() {
		ui.printFullLine('*');
		ui.wrappedPrint('*', "");
		ui.wrappedPrint('*', "Thanks");
		ui.wrappedPrint('*', "for playing");
		ui.wrappedPrint('*', "Minesweeper for Command Line!");
		ui.wrappedPrint('*', "");
		ui.printFullLine('*');
		
	}

	private static void playAgain() {
		ui.centerPrint("Play again?");
		
		
	}

	private static void displayGameOver(int state) {
		// TODO Auto-generated method stub
		
	}

	private static void makeMove() throws MinesweeperException {
		// TODO Auto-generated method stub
		
	}

	private static void displayGrid() {
		// TODO Auto-generated method stub
		
	}

	private static void getSettings() {
		// TODO Auto-generated method stub
		
	}



}
