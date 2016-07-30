package main;
import minesweeper.Minesweeper;
import minesweeper.exeptions.MineRevealedException;
import minesweeper.exeptions.MinesweeperException;
import minesweeper.exeptions.QuitGameException;
import userinterface.Command;
import userinterface.TextUI;

public class Main {
	
	private static boolean gameOver;
	private static boolean playing;
	private static Minesweeper game;
	private static TextUI ui;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ui = new TextUI(System.in, System.out);
		
		displayWelcome();
		
		playing=true;
		while (playing){
			
			getSettings();
			
			gameOver=false;
			while(!gameOver){
				
				displayGrid();
				getUsersMove();
				
			}
		}
		System.exit(0);
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

	private static void getSettings() {

		int x,y,mine;
		x = ui.nextBountedInt("How wide?", 1,Minesweeper.MAXSIZE);
		y = ui.nextBountedInt("How tall?", 1,Minesweeper.MAXSIZE);
		mine = ui.nextBountedInt("How Many Mines?", 1,(x*y-1));
		
		game = new Minesweeper(x, y, mine);
		
	}


	private static void displayGrid() {

		ui.print(game.printGrid());
	}

	private static void getUsersMove() {
		
		Command move = new Command(
				
				);
		
	}

	private static void displayGameOver(Minesweeper.gameState state) {
		// TODO Auto-generated method stub
		
	}

	private static void playAgain() {
		boolean illegalArgument = true;
		do {
			try {
				ui.centerPrint("Play again?");
				String input = ui.inputPrompt("(y,n): ");
				if(input.toLowerCase().charAt(0) == 'y'){
					assert(playing == true);
					assert(gameOver == false);
				} else if (input.toLowerCase().charAt(0) == 'n'){
					quitGame();
				} else {
					throw new IllegalArgumentException();
				}
			} catch (IllegalArgumentException e) {
				ui.centerPrint("I'm sorry, I didn't understand that. Please try again.");
			} 
		} while (illegalArgument);
	}

	private static void quitGame() {
		displayGoodbye();
		playing =false;
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
}
