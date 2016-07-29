package minesweeper.exeptions;

@SuppressWarnings("serial")
public class QuitGameException extends MinesweeperException {

	public QuitGameException() {
		super("quit");
	}
}
