package minesweeper.cells;

public abstract class AbstractCell {

	public enum CellState {HIDDEN, REVEALED, FLAGGED;}
	private CellState state;
	public AbstractCell(){
		state=CellState.HIDDEN;
		
	}
	
	public CellState getState() {
		return state;
	}

	protected void setState(CellState state) {
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
	
	public void clickCell(){
		if (state==CellState.FLAGGED){
			return; //do nothing
		} else {
			setState(CellState.REVEALED);
		}
	}
	
	/*
	 * end of game reveal
	 */
	public void revealCell() {

		this.setState(CellState.REVEALED);
		
	}
	
	public boolean isHidden() {
		return state==CellState.HIDDEN;
	}

	public boolean isFlagged(){
		return state==CellState.FLAGGED;
	}

	abstract public boolean isMine();

	abstract public char getSymbol();

	abstract public int getClue();

}
