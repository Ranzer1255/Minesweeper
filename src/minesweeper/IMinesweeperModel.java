package minesweeper;

public interface IMinesweeperModel{

	public void newGame(int x, int y, int numMines);
	
	public Field getMinefield();
	
	public void clickCell(int x, int y);

	public void flagCell(int x, int y);
	
	public void regFieldObserver(IFieldObserver fo);
	
	public void remFieldObserver(IFieldObserver fo);

	public StringBuilder printGrid();
	
}
