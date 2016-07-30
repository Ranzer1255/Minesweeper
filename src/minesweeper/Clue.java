package minesweeper;

public class Clue extends Cell {
	
	private int clue;
	
	public Clue(){
		this.clue=0;
	}
	
	public Clue(int clue){
		this.clue=clue;
	}

	@Override
	public boolean isMine() {
		
		return false;
	}

	@Override
	public char getSymbol() {
		
		return Integer.toString(clue).charAt(0);
	}

	@Override
	public void revealCell() {

		this.state=CellState.REVEALED;
		
	}

}
