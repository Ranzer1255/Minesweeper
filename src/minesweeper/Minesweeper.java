package minesweeper;

/**
 * @author Bobby Dillingham
 *
 */
public class Minesweeper implements IMinesweeperModel{

	public enum gameState{WIN, LOSE;}
	public static final int MAXSIZE = 26;
	private Field mineField;

	public Minesweeper(int x, int y, int numMines){

		mineField = new Field(x,y,numMines);
		
	}	

	public StringBuilder printGrid() {
		
		return mineField.toGridString();
	}

	@Override
	public void newGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Field getMinefield() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clickCell(int x, int y) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void flagCell(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void regFieldObserver(IFieldObserver fo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remFieldObserver(IFieldObserver fo) {
		// TODO Auto-generated method stub
		
	}


}
