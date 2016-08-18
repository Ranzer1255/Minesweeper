package minesweeper;

public class Mine extends AbstractCell {

	@Override
	public boolean isMine() {
		
		return true;
	}

	@Override
	public char getSymbol() {
		
		return '@';
	}

	@Override
	public void revealCell() {
				
	}

}
