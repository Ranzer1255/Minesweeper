package minesweeper.interfaces;

import minesweeper.Field;
import minesweeper.Location;
import minesweeper.Minesweeper.GameSettings;

public interface IMinesweeperModel extends IFieldObservable{

	@Deprecated
	public void newGame(int row, int col, int numMines);
	
	public void newGame(GameSettings settings);
	
	public void clickCell(Location c);

	public void flagCell(Location c);

	public Field getMinefield();
	
	public void setControler(IGameController controler);

	/*
	 * this might not be needed in the future. its only here for convenance
	 * for the text based display
	 */
	public StringBuilder printGrid();

	public int getRemainingMines();
	
}
