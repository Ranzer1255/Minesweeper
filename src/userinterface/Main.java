package userinterface;

import minesweeper.IMinesweeperModel;
import minesweeper.IGameController;
import minesweeper.Minesweeper;

public class Main {

	public static void main(String[] args) {
		
		IMinesweeperModel game = new Minesweeper(); 

		IGameController ui = new TUIController(System.in, System.out, game);
		
		ui.newGame();
	}
}
