package minesweeper;

public abstract class Cell {

	private CellState state;
	
	public Cell(){
		state=CellState.HIDDEN;
		
	}
	
	public char getChar() {

		if (state==CellState.HIDDEN)
			return '*';
		else
			return this.getSymbol();
	}
	
	abstract public boolean isMine();

	abstract public char getSymbol();

	private enum CellState {
		HIDDEN, REVEALED;
	}

}
