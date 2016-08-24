package minesweeper;


/**
 * @author Bobby Dillingham
 *
 */
public class Minesweeper implements IMinesweeperModel{

	public enum gameState{WIN, LOSE;}
	public static final int MAXSIZE = 26;
	
	
	private Field mineField;
	private IGameController controler;
	
	
	public Minesweeper() {}//TODO

	public Minesweeper(int x, int y, int numMines){
		
		mineField = new Field(x,y,numMines);
	}
	
	public void setControler(IGameController controler){
		this.controler = controler;
	}

	@Override
	public void newGame(int x, int y, int numMines) {
		mineField = new Field(x,y,numMines);
		
		//TODO?
		
	}

	@Override
	public Field getMinefield() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clickCell(int x, int y) {

		if (mineField.getCell(x, y).isMine()) {
			gameLose();
		} else {
			mineField.clickCell(new Location(x,y));
		} 
	}

	@Override
	public void flagCell(int x, int y) {

		mineField.flagCell(x, y);
		
	}

	private void gameLose() {
		
		mineField.revealAll();
		controler.gameLose();
		
	}

	public StringBuilder printGrid() {
		
		return mineField.toGridString();
	}

	@Override
	public void regFieldObserver(IFieldObserver fo) {
		mineField.regFieldObserver(fo);
	}

	@Override
	public void remFieldObserver(IFieldObserver fo) {
		mineField.remFieldObserver(fo);
	}
}
