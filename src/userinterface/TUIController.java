package userinterface;

import java.io.InputStream;
import java.io.PrintStream;
import minesweeper.IMinesweeperModel;
import minesweeper.Field;
import minesweeper.IGameController;
import minesweeper.Minesweeper;

public class TUIController implements IGameController{
	
	private IMinesweeperModel game;
	private TUIView ui;

	public TUIController(InputStream in, PrintStream out, IMinesweeperModel game) {
		this.ui = new TUIView(in, out);
		this.game = game;
		this.game.regFieldObserver(this);
	}


	@Override	
	public void newGame(){
		this.getSettings();
		this.startGame();
	}


	@Override
	public void gameWin() {
		//TODO
		ui.displayWinGameOver();
//		return this.playAgain();
		
	}


	@Override
	public void gameLose() {
		//TODO
		ui.displayLoseGameOver();
//		return this.playAgain();
		
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

	public void quitGame() {
		ui.displayGoodbye();
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
		System.exit(0);
	}




	private void startGame() {
		
	}


	@Override
	public void update() {

		displayGrid();		
	}


	@Override
	public void update(Field f) {}// NO-OP


	private void displayGrid() {
	
		ui.print(game.printGrid());
	}
}
