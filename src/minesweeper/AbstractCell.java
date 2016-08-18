package minesweeper;

public abstract class AbstractCell {

	public enum CellState {HIDDEN, REVEALED, FLAGGED;}
	private CellState state;
	
	public AbstractCell(){
		state=CellState.HIDDEN;
		
	}
	
	public CellState getState() {
		return state;
	}

	public void setState(CellState state) {
		this.state = state;
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
		
	abstract public void revealCell();
	
	abstract public boolean isMine();

	abstract public char getSymbol();

}
