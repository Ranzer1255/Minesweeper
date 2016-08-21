package userinterface;
import java.io.InputStream;
import java.io.PrintStream;

import minesweeper.IMinesweeperModel;
import minesweeper.Minesweeper;

public class TUIController implements ITUIController{
	
	private static boolean gameOver;
	private static boolean playing;
	private static Minesweeper game;
	private static TUIView ui;

	public TUIController(InputStream in, PrintStream out, IMinesweeperModel game2) {
		this.ui = new TUIView(in, out);
		
	}


	/**
	 * @param args
	 */

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

//	private static Command getUsersMove() {
//		
//		Command rtn = new Command();//TODO 
//		
//		
//		
//		return rtn;
//	}

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
		ui.displayGoodbye();
		playing =false;
	}



	@Override
	public void startGame() {
		// TODO Auto-generated method stub
		
	}
}
