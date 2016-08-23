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
	private Field mineField;
	private IGameController controler;
	
	
	public Minesweeper() {}

	public Minesweeper(int x, int y, int numMines){
		
		mineField = new Field(x,y,numMines);
	}
	
	public void setControler(IGameController controler){
		this.controler = controler;
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

		if (mineField.getCell(x, y).isMine()) {
			gameLose();
		} else {
			mineField.clickCell(x, y);
		} 
		updateObservers();
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
	public void regFieldObserver(IFieldObserver fo) {fos.add(fo);}

	@Override
	public void remFieldObserver(IFieldObserver fo) {fos.remove(fo);}
	
	private void updateObservers(){
		for (IFieldObserver fo : fos) {
			fo.update();
		}
	}
}
