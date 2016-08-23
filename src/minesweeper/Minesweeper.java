package minesweeper;

import java.util.ArrayList;

/**
 * @author Bobby Dillingham
 *
 */
public class Minesweeper implements IMinesweeperModel{

	public enum gameState{WIN, LOSE;}
	public static final int MAXSIZE = 26;
	
	
	private ArrayList<IFieldObserver> fos;
	private boolean gameOver;
	private boolean playing;
	private Field mineField;
	
	
	public Minesweeper() {}

	public Minesweeper(int x, int y, int numMines){

		mineField = new Field(x,y,numMines);
		
	}	

	@Override
	public void newGame(int x, int y, int numMines) {
		mineField = new Field(x,y,numMines);
		updateObservers();
		
	}

	@Override
	public Field getMinefield() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clickCell(int x, int y) {
		mineField.clickCell(x,y);
	
	}

	@Override
	public void flagCell(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public StringBuilder printGrid() {
		
		return mineField.toGridString();
	}

	@Override
	public void regFieldObserver(IFieldObserver fo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remFieldObserver(IFieldObserver fo) {
		// TODO Auto-generated method stub
		
	}
	
	private void updateObservers(){//TODO
		for (IFieldObserver fo : fos) {
			fo.update();
		}
	}


}
