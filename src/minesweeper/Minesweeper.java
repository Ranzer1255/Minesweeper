package minesweeper;
/**
 * 
 */

/**
 * @author Bobby Dillingham
 *
 */
public class Minesweeper {
	
	Field pf;
	
	
	public Minesweeper(int x, int y, int numMines){

		pf = new Field(x,y,numMines);
		
	}
	
	

	private enum gameState{
		WIN, LOSE;
	}
}
