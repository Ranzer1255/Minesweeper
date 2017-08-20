package minesweeper.interfaces;

public interface IGameController extends IFieldObserver {
	
	/**
	 * start a new game
	 * will be called by the model but can be called
	 * if called externally this should wipe the existing game
	 */
	public void newGame();
	
	/**
	 * call back method when the game is won
	 * will be called by the model
	 */
	public void gameWin();
	
	/**
	 * callback method when the game is lost
	 * will be called by the model
	 */
	public void gameLose();
}
