package textInterface;

import minesweeper.Minesweeper;
import minesweeper.interfaces.IGameController;
import minesweeper.interfaces.IMinesweeperModel;

public class Main {

	public static void main(String[] args) {
		
		IMinesweeperModel game = new Minesweeper(); 

		IGameController ui = new TUIController(System.in, System.out, game);
		
		ui.newGame();
	}
}
