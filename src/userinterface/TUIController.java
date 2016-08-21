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


	private void displayGameOver(Minesweeper.gameState state) {
		// TODO Auto-generated method stub
		
	}



	private void quitGame() {
		ui.displayGoodbye();
		System.exit(0);
	}



	@Override
	public void startGame() {
		// TODO Auto-generated method stub
		
	}
	
	public void newGame(){
		// TODO
	}
}
