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
	public void clickCell(int x, int y) {

		if (mineField.getCell(x, y).isMine()) {
			gameLose();
		} else {
			mineField.clickCell(x,y);
			if (fieldIsClear()){
				gameWin();
			}
		}
	}

	private boolean fieldIsClear() {
		return mineField.isClear();
	}

	private void gameWin() {
		mineField.revealAll();
		controler.gameWin();
		//TODO
		
	}

	private void gameLose() {
		
		mineField.revealAll();
		controler.gameLose();
		//TODO
	}

	@Override
	public void flagCell(int x, int y) {

		mineField.flagCell(x, y);
		
	}

	public StringBuilder printGrid() {
		
		return mineField.toGridString();
	}

	@Override
	public Field getMinefield() {
		
		return mineField;
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
