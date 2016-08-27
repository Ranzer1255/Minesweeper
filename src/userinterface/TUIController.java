package userinterface;

import java.io.InputStream;
import java.io.PrintStream;
import minesweeper.IMinesweeperModel;
import minesweeper.IGameController;
import minesweeper.Minesweeper;

public class TUIController implements IGameController{
	
	private IMinesweeperModel game;
	private TUIView ui;
	private boolean playing;

	public TUIController(InputStream in, PrintStream out, IMinesweeperModel game) {
		this.ui = new TUIView(in, out);
		this.game = game;
		this.game.regFieldObserver(this);
		this.game.setControler(this);
	}


	@Override	
	public void newGame(){
		ui.displayWelcome();
		this.getSettings();
		this.startGame();
	}


	@Override
	public void gameWin() {
		
		ui.displayWinGameOver();
		playing = playAgain();
		if(playing) getSettings();
		
	}

	@Override
	public void gameLose() {
		
		ui.displayLoseGameOver();
		playing = playAgain();
		if(playing) getSettings();
		
	}

	private boolean playAgain() {
		boolean rtn = false;
		boolean invalidArg;
		do {
			invalidArg = false;
			String in = ui.playAgain();
			try{
				if (in.toLowerCase().charAt(0)== 'y'){
					rtn = true;
				} else if (in.toLowerCase().charAt(0) == 'n'){
					rtn = false;
				} else {
					invalidArg=true;
					throw new IllegalArgumentException("Please answer Yes or No");
				}
			}catch (IllegalArgumentException e){
				ui.badInput(e.getMessage());
			}
			
		} while (invalidArg);
		
		return rtn;
	}


	/**
	 * @param args
	 */

	private void getSettings() {

		int x,y,mine;
		x = ui.getX(Minesweeper.MAXSIZE);
		y = ui.getY(Minesweeper.MAXSIZE);
		mine = ui.getMines(x*y-1);
		
		game.newGame(x, y, mine);
		
	}

	public void quitGame() {
		ui.displayGoodbye();
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
		System.exit(0);
	}




	private void startGame() {
		playing = true;
		displayGrid();
		while (playing){
			parseInput(ui.nextCommand());
		}
		quitGame();
	}


	private void parseInput(String nextCommand) {
		try {
			int input = Integer.parseInt(nextCommand);
			switch (input){
			case 1:
				game.clickCell(ui.getLoc());
				break;
			case 2:
				game.flagCell(ui.getLoc());
				break;
			case 3:
				quitGame();
				break;
			default:
				throw new NumberFormatException("Please enter a valid choice");
			}
		} catch (NumberFormatException e) {
			ui.badInput(e.getMessage());
		}
		
	}


	@Override
	public void update() {

		displayGrid();		
	}


	private void displayGrid() {
	
		ui.print(game.printGrid());
	}
}
