package minesweeper;

/**
 * @author Bobby Dillingham
 *
 */
public class Minesweeper {
	
	public static final int MAXSIZE = 26;
	
	private Field mineField;

	
	public Minesweeper(int x, int y, int numMines){

		mineField = new Field(x,y,numMines);
		
	}
	
	

	public enum gameState{
		WIN, LOSE;
	}



	public StringBuilder printGrid() {
		
		return mineField.toGridString();
	}
}
