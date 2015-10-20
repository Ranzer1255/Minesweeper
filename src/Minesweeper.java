/**
 * 
 */

/**
 * @author Bobby Dillingham
 *
 */
public class Minesweeper {

	//size of play field
	private int x;
	private int y;
	private int numMines;
	
	
	public Minesweeper(int x, int y, int numMines){
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Minesweeper game  = new Minesweeper();
		
		//welcome players
		game.welcome();
		
		game.init();
		
		game.dispalyField()
		
		
	}

}
