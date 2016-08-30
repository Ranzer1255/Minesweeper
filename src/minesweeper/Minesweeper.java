package minesweeper;

import java.util.ArrayList;

import minesweeper.interfaces.IFieldObserver;
import minesweeper.interfaces.IGameController;
import minesweeper.interfaces.IMinesweeperModel;

/**
 * @author Bobby Dillingham
 *
 */
public class Minesweeper implements IMinesweeperModel,IFieldObserver{

	private Field mineField;
	private IGameController controler;
	private ArrayList<IFieldObserver> fos;
	
	
	public Minesweeper() {
		fos = new ArrayList<>();
		
	}
	
	public void setControler(IGameController controler){
		this.controler = controler;
	}

	@Override
	public void newGame(int row, int col, int numMines) {
		mineField = instanciateNewField(row,col,numMines);
	}

	@Override
	public void clickCell(Location c) {

		if (mineField.clickCell(c)) {
			gameLose();
		}
		if (fieldIsClear()){
			gameWin();
		}
	}

	private Field instanciateNewField(int row, int col, int numMines) {
		Field rtn = new Field(row, col, numMines);
		rtn.regFieldObserver(this);
		return rtn;
	}

	private boolean fieldIsClear() {
		return mineField.isClear();
	}

	private void gameWin() {
		mineField.revealAll();
		controler.gameWin();
		
	}

	private void gameLose() {
		
		mineField.revealAll();
		controler.gameLose();
	}

	@Override
	public void flagCell(Location c) {

		mineField.flagCell(c);
		
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
		fos.add(fo);
	}

	@Override
	public void remFieldObserver(IFieldObserver fo) {
		fos.remove(fo);
	}

	@Override
	public void update() {
		for (IFieldObserver fo : fos) {
			fo.update();
		}
	}

	@Override
	public int getRemainingMines() {
		return mineField.getMines()-mineField.getFlags();
	}
}
