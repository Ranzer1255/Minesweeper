package minesweeper;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Field {

	private Cell[][] field;
	private int sizeX,sizeY,mines;
	
	public Field(int x, int y, int mines){
		
		this.sizeX=x; this.sizeY=y;		
		this.mines=mines;
		instanciateCellArray(x, y);
		setMines();
		setClues();
		
	}

	public Cell getCell(int x, int y) {		
		return field[x][y];
	}

	private void instanciateCellArray(int x, int y) {
		field=new Cell[x][y];
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
				randX = randomInt(sizeX+1); randY = randomInt(sizeY+1);
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
					ArrayList<Cell> neighbors = getNeighborsOfCell(i, j);
					int count=0;
					for (Cell c : neighbors) {
						if (c.isMine()) {
							count++;
						}
					}
					field[i][j] = new Clue(count);
				}
			}
		}
	}

	private ArrayList<Cell> getNeighborsOfCell(int x, int y) {
		ArrayList<Cell> rtn = new ArrayList<Cell>();
		
		if((x==0&&y==0)){ 				//Northwest corner
			rtn.add(getCell(x  , y+1));
			rtn.add(getCell(x+1, y  ));
			rtn.add(getCell(x+1, y+1));
		} else if (x==0&&y==sizeY){		//Northeast corner
			rtn.add(getCell(x  , y-1));
			rtn.add(getCell(x+1, y  ));
			rtn.add(getCell(x+1, y-1));
		} else if (x==sizeX&&y==0){		//Southwest corner
			rtn.add(getCell(x  , y+1));
			rtn.add(getCell(x-1, y  ));
			rtn.add(getCell(x-1, y+1));
		} else if (x==sizeX&&y==sizeY){	//Southeast corner
			rtn.add(getCell(x  , y-1));
			rtn.add(getCell(x-1, y  ));
			rtn.add(getCell(x-1, y-1));
		} else if (x==0){				//North edge
			rtn.add(getCell(x  , y-1));
			rtn.add(getCell(x  , y+1));
			rtn.add(getCell(x+1, y  ));
			rtn.add(getCell(x+1, y-1));
			rtn.add(getCell(x+1, y+1));
		} else if (x==sizeX){			//South edge
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
		} else if (y==sizeY){			//East edge
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

	public StringBuilder toGridString() {
		StringBuilder rtn=new StringBuilder();
		
		//top ruller
		
		rtn.append('*');
		rtn.append('|');
		for (int i = 0; i < sizeX; i++) {
			rtn.append((char)('a'+i));
		}
		rtn.append('|');
		rtn.append('*');
		rtn.append('\n');
		
		
		//top border
		rtn.append('-');
		rtn.append('|');
		for (int i = 0; i < sizeX; i++) {
			rtn.append('-');
		}
		rtn.append('|');
		rtn.append('-');
		rtn.append("\n");
		
		//Grid
		for (int i = 0; i < field.length; i++) {
			
			rtn.append((char)('a'+i));
			rtn.append('|');
			for (int j = 0; j < field[i].length; j++) {
				rtn.append(getCell(i, j).getChar());
			}
			rtn.append("| \n");
		}
		
		//bottom border
		rtn.append('-');
		rtn.append('|');
		for (int i = 0; i < sizeX; i++) {
			rtn.append('-');
		}
		rtn.append('|');
		rtn.append('-');
		rtn.append("\n");
		
		return rtn;
	}
}
