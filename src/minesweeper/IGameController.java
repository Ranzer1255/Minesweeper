package minesweeper;

public interface IGameController extends IFieldObserver {
	
	public void newGame();
	
	public void gameWin();
	
	public void gameLose();
}
