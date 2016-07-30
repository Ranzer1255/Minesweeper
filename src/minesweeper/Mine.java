package minesweeper;

import minesweeper.exeptions.MineRevealedException;

public class Mine extends Cell {

	@Override
	public boolean isMine() {
		
		return true;
	}

	@Override
	public char getSymbol() {
		
		return '@';
	}

	@Override
	public void revealCell() throws MineRevealedException {
		
		throw new MineRevealedException();
		
	}

}
