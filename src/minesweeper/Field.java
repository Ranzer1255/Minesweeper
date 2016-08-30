package minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import minesweeper.cells.*;
import minesweeper.interfaces.IFieldObservable;
import minesweeper.interfaces.IFieldObserver;

public class Field implements IFieldObservable{

	private AbstractCell[][] field;
	private int sizeRow,sizeCol,mines,clues;
	private List<IFieldObserver> fos;
	
	public Field(int row, int col, int mines){
		
		fos=new ArrayList<>();
		this.sizeRow=row; this.sizeCol=col;		
		this.mines=mines;
		this.clues = (row*col)-mines;
		instanciateCellArray(row, col);
		setMines();
		setClues();
		
	}

	@Deprecated
	public AbstractCell getCell(int row, int col) {
		return getCell(new Location(row,col));
	}

	public AbstractCell getCell(Location c) {	
		return field[c.getRow()][c.getCol()];
	}
	@Deprecated
	public void clickCell(int row, int col){
		clickCell(new Location(row, col));
	}
	
	/**
	 * 
	 * @param c Location of the Cell you need to click
	 * @return True if Cell is a mine
	 */
	public boolean clickCell(Location c) {
	
		if		(getCell(c).isFlagged())return false;
		else if	(getCell(c).isMine())	return true;
		else{
			clues--;
			
			getCell(c).clickCell();
		
			updateObservers();		
			if (getCell(c).getClue()==0) {
				List <Location> nighbors = getNeighborsOfCell(c);
				for (Location n : nighbors) {
					if(getCell(n).isHidden()) clickCell(n);
				}
			}
			return false;
		}
		
	}

	@Deprecated
	public void flagCell(int row, int col){
		field[row][col].toggleCellFlag();
		updateObservers();
	}
	public void flagCell(Location c){
		field[c.getRow()][c.getCol()].toggleCellFlag();
		updateObservers();
	}

	public boolean isClear() {
		return clues==0;
	}

	/*
	 * end of game reveal.
	 */
	public void revealAll() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				field[i][j].revealCell();
			}
		}
		updateObservers();
	}

	/*
	 *if this gets redone at some time. pass the entire grid as a string of symbols and let the view parse that string and format it
	 *
	 *but for now its fine as is and i see no need for it to be changed this keeps it well encapsulated, the Field class has the 
	 *information needed to easily form the grid
	 */
	public StringBuilder toGridString() {
		StringBuilder rtn=new StringBuilder();
		
		//top ruller
		
		rtn.append("* ");
		rtn.append("| ");
		for (int i = 0; i < sizeCol; i++) {
			rtn.append((char)('a'+i));
			rtn.append(" ");
		}
		rtn.append("| ");
		rtn.append("*");
		rtn.append('\n');
		
		
		//top border
		rtn.append("- ");
		rtn.append("- ");
		for (int i = 0; i < sizeCol; i++) {
			rtn.append("- ");
		}
		rtn.append("| " );
		rtn.append("- ");
		rtn.append("\n");
		
		//Grid
		for (int i = 0; i < field.length; i++) {
			
			rtn.append((char)('a'+i));
			rtn.append(" | ");
			for (int j = 0; j < field[i].length; j++) {
				rtn.append(field[i][j].getChar());
				rtn.append(" ");
			}
			rtn.append("|\n");
		}
		
		//bottom border
		rtn.append("- | ");
		for (int i = 0; i < sizeCol; i++) {
			rtn.append("- ");
		}
		rtn.append("| -\n");
		
		rtn.append("* ");
		rtn.append("| ");
		for (int i = 0; i < sizeCol; i++) {
			rtn.append((char)('a'+i));
			rtn.append(" ");
		}
		rtn.append("| ");
		rtn.append("*");
		rtn.append('\n');
		
		return rtn;
	}

	private void instanciateCellArray(int row, int col) {
		field=new AbstractCell[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				field[i][j] = new Clue();
			}
		}
	}

	private void setMines() {
		for (int i = 0; i < this.mines; i++) {
			//get random cell
			int randRow, randCol;
			do {
				randRow = randomInt(sizeRow); randCol = randomInt(sizeCol);
			}while(getCell(randRow, randCol).isMine());
			field[randRow][randCol] = new Mine();
		}
	}

	private void setClues() {
		for (int i = 0; i < sizeRow; i++) {
			for (int j = 0; j < sizeCol; j++) {
				
				if(!getCell(i,j).isMine()){
					List<Location> neighbors = getNeighborsOfCell(new Location(i,j));
					int count=0;
					for (Location c : neighbors) {
						if (getCell(c).isMine()) {
							count++;
						}
					}
					field[i][j] = new Clue(count);
				}
			}
		}
	}

	private int randomInt(int i) {
		return ThreadLocalRandom.current().nextInt(i);
	}

	private List<Location> getNeighborsOfCell(Location origin) {
		List<Location> rtn = new ArrayList<Location>();
		int row = origin.getRow(); int col = origin.getCol();
		
		if((row==0&&col==0)){ 				//Northwest corner
			rtn.add(new Location(row  , col+1));
			rtn.add(new Location(row+1, col  ));
			rtn.add(new Location(row+1, col+1));
		} else if (row==0&&col==sizeCol-1){		//Northeast corner
			rtn.add(new Location(row  , col-1));
			rtn.add(new Location(row+1, col  ));
			rtn.add(new Location(row+1, col-1));
		} else if (row==sizeRow-1&&col==0){		//Southwest corner
			rtn.add(new Location(row  , col+1));
			rtn.add(new Location(row-1, col  ));
			rtn.add(new Location(row-1, col+1));
		} else if (row==sizeRow-1&&col==sizeCol-1){	//Southeast corner
			rtn.add(new Location(row  , col-1));
			rtn.add(new Location(row-1, col  ));
			rtn.add(new Location(row-1, col-1));
		} else if (row==0){				//North edge
			rtn.add(new Location(row  , col-1));
			rtn.add(new Location(row  , col+1));
			rtn.add(new Location(row+1, col  ));
			rtn.add(new Location(row+1, col-1));
			rtn.add(new Location(row+1, col+1));
		} else if (row==sizeRow-1){			//South edge
			rtn.add(new Location(row  , col-1));
			rtn.add(new Location(row  , col+1));
			rtn.add(new Location(row-1, col  ));
			rtn.add(new Location(row-1, col-1));
			rtn.add(new Location(row-1, col+1));
		} else if (col==0) {				//West edge
			rtn.add(new Location(row-1, col  ));
			rtn.add(new Location(row-1, col+1));
			rtn.add(new Location(row  , col+1));
			rtn.add(new Location(row+1, col  ));
			rtn.add(new Location(row+1, col+1));
		} else if (col==sizeCol-1){			//East edge
			rtn.add(new Location(row-1, col  ));
			rtn.add(new Location(row-1, col-1));
			rtn.add(new Location(row  , col-1));
			rtn.add(new Location(row+1, col  ));
			rtn.add(new Location(row+1, col-1));
		} else {						//middle
			rtn.add(new Location(row-1, col  ));
			rtn.add(new Location(row-1, col-1));
			rtn.add(new Location(row-1, col+1));
			rtn.add(new Location(row  , col-1));
			rtn.add(new Location(row  , col+1));
			rtn.add(new Location(row+1, col  ));
			rtn.add(new Location(row+1, col-1));
			rtn.add(new Location(row+1, col+1));
		}
		return rtn;
	}

	@Override
	public void regFieldObserver(IFieldObserver fo) {fos.add(fo);}

	@Override
	public void remFieldObserver(IFieldObserver fo) {fos.remove(fo);}
	
	private void updateObservers(){
		for (IFieldObserver fo : fos) {
			fo.update();
		}
	}
}
