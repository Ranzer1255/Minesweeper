package minesweeper;

@SuppressWarnings("serial")
public class QuitGameException extends MinesweeperException {

	public QuitGameException() {
		super("quit");
	}
}
