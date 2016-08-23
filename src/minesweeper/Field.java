package minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import cells.AbstractCell;
import cells.Clue;
import cells.Mine;
import cells.AbstractCell.CellState;

public class Field {

	private AbstractCell[][] field;
	private int sizeX,sizeY,mines;
	
	public Field(int x, int y, int mines){
		
		this.sizeX=x; this.sizeY=y;		
		this.mines=mines;
		instanciateCellArray(x, y);
		setMines();
		setClues();
		
	}

	public AbstractCell getCell(int x, int y) {	
		return field[x][y];
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
		for (int i = 0; i < sizeX; i++) {
			rtn.append((char)('a'+i));
			rtn.append(" ");
		}
		rtn.append("| ");
		rtn.append("*");
		rtn.append('\n');
		
		
		//top border
		rtn.append("- ");
		rtn.append("- ");
		for (int i = 0; i < sizeX; i++) {
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
				rtn.append(getCell(i, j).getChar());
				rtn.append(" ");
			}
			rtn.append("|\n");
		}
		
		//bottom border
		rtn.append("* | ");
		for (int i = 0; i < sizeX; i++) {
			rtn.append("- ");
		}
		rtn.append("| *\n");
		
		return rtn;
	}

	private void instanciateCellArray(int x, int y) {
		field=new AbstractCell[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				field[i][j] = new Clue();
			}
		}
	}

	private void setMines() {
		for (int i = 0; i < this.mines; i++) {
			//get random cell
			int randX, randY;
			do {
				randX = randomInt(sizeX); randY = randomInt(sizeY);
			}while(getCell(randX, randY).isMine());
			field[randX][randY] = new Mine();
		}
	}

	private int randomInt(int i) {
		return ThreadLocalRandom.current().nextInt(i);
	}

	private void setClues() {
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				
				if(!getCell(i,j).isMine()){
					List<AbstractCell> neighbors = getNeighborsOfCell(i, j);
					int count=0;
					for (AbstractCell c : neighbors) {
						if (c.isMine()) {
							count++;
						}
					}
					field[i][j] = new Clue(count);
				}
			}
		}
	}

	private List<AbstractCell> getNeighborsOfCell(int x, int y) {
		List<AbstractCell> rtn = new ArrayList<AbstractCell>();
		
		if((x==0&&y==0)){ 				//Northwest corner
			rtn.add(getCell(x  , y+1));
			rtn.add(getCell(x+1, y  ));
			rtn.add(getCell(x+1, y+1));
		} else if (x==0&&y==sizeY-1){		//Northeast corner
			rtn.add(getCell(x  , y-1));
			rtn.add(getCell(x+1, y  ));
			rtn.add(getCell(x+1, y-1));
		} else if (x==sizeX-1&&y==0){		//Southwest corner
			rtn.add(getCell(x  , y+1));
			rtn.add(getCell(x-1, y  ));
			rtn.add(getCell(x-1, y+1));
		} else if (x==sizeX-1&&y==sizeY-1){	//Southeast corner
			rtn.add(getCell(x  , y-1));
			rtn.add(getCell(x-1, y  ));
			rtn.add(getCell(x-1, y-1));
		} else if (x==0){				//North edge
			rtn.add(getCell(x  , y-1));
			rtn.add(getCell(x  , y+1));
			rtn.add(getCell(x+1, y  ));
			rtn.add(getCell(x+1, y-1));
			rtn.add(getCell(x+1, y+1));
		} else if (x==sizeX-1){			//South edge
			rtn.add(getCell(x  , y-1));
			rtn.add(getCell(x  , y+1));
			rtn.add(getCell(x-1, y  ));
			rtn.add(getCell(x-1, y-1));
			rtn.add(getCell(x-1, y+1));
		} else if (y==0) {				//West edge
			rtn.add(getCell(x-1, y  ));
			rtn.add(getCell(x-1, y+1));
			rtn.add(getCell(x  , y+1));
			rtn.add(getCell(x+1, y  ));
			rtn.add(getCell(x+1, y+1));
		} else if (y==sizeY-1){			//East edge
			rtn.add(getCell(x-1, y  ));
			rtn.add(getCell(x-1, y-1));
			rtn.add(getCell(x  , y-1));
			rtn.add(getCell(x+1, y  ));
			rtn.add(getCell(x+1, y-1));
		} else {						//middle
			rtn.add(getCell(x-1, y  ));
			rtn.add(getCell(x-1, y-1));
			rtn.add(getCell(x-1, y+1));
			rtn.add(getCell(x  , y-1));
			rtn.add(getCell(x  , y+1));
			rtn.add(getCell(x+1, y  ));
			rtn.add(getCell(x+1, y-1));
			rtn.add(getCell(x+1, y+1));
		}
		return rtn;
	}

	public void clickCell(int x, int y) {

		field[x][y].revealCell();
		
	}
	
	public void flagCell(int x, int y){
		field[x][y].toggleCellFlag();
	}
}
