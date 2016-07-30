package minesweeper;

import minesweeper.exeptions.MineRevealedException;

public abstract class Cell {

	protected CellState state;
	
	public Cell(){
		state=CellState.HIDDEN;
		
	}
	
	public char getChar() {

		if (state==CellState.HIDDEN)
			return '*';
		else if (state==CellState.FLAGGED)
			return '!';
		else
			return this.getSymbol();
	}
	
	public void toggleCellFlag() {
		
		if (state==CellState.REVEALED){
			return; //if revealed, ignore the call
		}else if (state==CellState.HIDDEN){
			state = CellState.FLAGGED;
		}else{
			state=CellState.HIDDEN;
		}
		
	}
		
	abstract public void revealCell() throws MineRevealedException;
	
	abstract public boolean isMine();

	abstract public char getSymbol();

	protected enum CellState {
		HIDDEN, REVEALED, FLAGGED;
	}

}
