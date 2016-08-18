package minesweeper;

public interface MinesweeperModelInterface{

	public void newGame();
	
	public Field getMinefield();
	
	public void clickCell(int x, int y);

	public void flagCell(int x, int y);
	
	public void regFieldObserver(FieldObserver fo);
	
	public void remFieldObserver(FieldObserver fo);
	
}
