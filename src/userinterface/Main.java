package userinterface;

import minesweeper.IMinesweeperModel;
import minesweeper.Minesweeper;

public class Main {

	public static void main(String[] args) {
		
		IMinesweeperModel game = new Minesweeper(); 

		ITUIController ui = new TUIController(System.in, System.out, game);
		
		ui.startGame();
		
		System.exit(0);
	}
}
