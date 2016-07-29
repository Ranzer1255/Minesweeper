package minesweeper;

public class Mine extends Cell {

	@Override
	public boolean isMine() {
		
		return true;
	}

	@Override
	public char getSymbol() {
		
		return '@';
	}

}
