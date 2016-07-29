package minesweeper;

public abstract class Cell {

	private CellState state;
	
	public Cell(){
		state=CellState.HIDDEN;
		
	}
	
	abstract public boolean isMine();
	
	abstract public char getSymbol();

	public char getChar() {

		if (state==CellState.HIDDEN)
			return ' ';
		else
			return this.getSymbol();
	}
	
	private enum CellState {
		HIDDEN, REVEALED;
	}

}
