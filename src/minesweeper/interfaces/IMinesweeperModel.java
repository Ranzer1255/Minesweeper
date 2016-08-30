package minesweeper.interfaces;

import minesweeper.Field;
import minesweeper.Location;

public interface IMinesweeperModel extends IFieldObservable{

	public void newGame(int row, int col, int numMines);
	
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
