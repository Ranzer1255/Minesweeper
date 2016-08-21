package userinterface;
import java.io.InputStream;
import java.io.PrintStream;

import minesweeper.IMinesweeperModel;
import minesweeper.Minesweeper;

public class TUIController implements ITUIController{
	
	private boolean gameOver;
	private boolean playing;
	private IMinesweeperModel game;
	private TUIView ui;

	public TUIController(InputStream in, PrintStream out, IMinesweeperModel game) {
		this.ui = new TUIView(in, out);
		this.game = game;
	}


	@Override	
	public void newGame(){
		this.getSettings();
		this.startGame();
	}


	@Override
	public boolean gameWin() {

		ui.displayWinGameOver();
		return this.playAgain();
		
	}


	@Override
	public boolean gameLose() {
		
		ui.displayLoseGameOver();
		return this.playAgain();
		
	}


	private boolean playAgain() {

		return false; //TODO
	}


	/**
	 * @param args
	 */

	private void getSettings() {

		int x,y,mine;
		x = ui.getX(Minesweeper.MAXSIZE);
		y = ui.getY(Minesweeper.MAXSIZE);
		mine = ui.getMines(x*y-1);
		
		game = new Minesweeper(x, y, mine);
		
	}

	private void displayGrid() {

		ui.print(game.printGrid());
	}

	private void quitGame() {
		ui.displayGoodbye();
		System.exit(0);
	}




	private void startGame() {
		
	}
}
