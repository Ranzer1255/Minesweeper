package cells;

public class Clue extends AbstractCell {
	
	private final int CLUE;
	
	public Clue(){
		this.CLUE=0;
	}
	
	public Clue(int clue){
		this.CLUE=clue;
	}

	@Override
	public boolean isMine() {
		
		return false;
	}

	@Override
	public char getSymbol() {
		
		return Integer.toString(CLUE).charAt(0);
	}

	@Override
	public void revealCell() {

		this.setState(CellState.REVEALED);
		
	}

}
