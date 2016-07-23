package minesweeper;

import java.util.ArrayList;
import java.util.Iterator;
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
				field[i][j] = new Cell();
			}
		}
	}

	private void setMines() {
		for (int i = 0; i < this.mines; i++) {
			//get random cell
			int randX = randomInt(sizeX+1), randY = randomInt(sizeY+1);
			
			if (getCell(randX, randY).isMine()){
				while(getCell(randX, randY).isMine()){
					randX = randomInt(sizeX+1); randY = randomInt(sizeY+1);
				}
			} else {
				field[randX][randY] = new Cell(CellType.MINE);
			}
		}
	}

	private int randomInt(int i) {
		return ThreadLocalRandom.current().nextInt(i);
	}

	private void setClues() {
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				
				if(!getCell(i,j).isMine()){
					ArrayList<Cell> nighbors = getNeighborsOfCell(i, j);
					int count=0;
					for (Cell c : nighbors) {
						if (c.isMine()) {
							count++;
						}
					}
					field[i][j] = new Cell(CellType.CLUE, count);
				}
			}
		}
	}

	private ArrayList<Cell> getNeighborsOfCell(int x, int y) {
		ArrayList<Cell> rtn = new ArrayList<Cell>();
		
		if((x==0&&y==0)){
			rtn.add(getCell(x  , y+1));
			rtn.add(getCell(x+1, y  ));
			rtn.add(getCell(x+1, y+1));
		} else if (x==0&&y==sizeY){
			rtn.add(getCell(x  , y-1));
			rtn.add(getCell(x+1, y  ));
			rtn.add(getCell(x+1, y-1));
		} else if (x==sizeX&&y==0){
			rtn.add(getCell(x  , y+1));
			rtn.add(getCell(x-1, y  ));
			rtn.add(getCell(x-1, y+1));
		} else if (x==sizeX&&y==sizeY){
			rtn.add(getCell(x  , y-1));
			rtn.add(getCell(x-1, y  ));
			rtn.add(getCell(x-1, y-1));
		} else if (x==0){
			rtn.add(getCell(x  , y-1));
			rtn.add(getCell(x  , y+1));
			rtn.add(getCell(x+1, y  ));
			rtn.add(getCell(x+1, y-1));
			rtn.add(getCell(x+1, y+1));
		} else if (x==sizeX){
			rtn.add(getCell(x  , y-1));
			rtn.add(getCell(x  , y+1));
			rtn.add(getCell(x-1, y  ));
			rtn.add(getCell(x-1, y-1));
			rtn.add(getCell(x-1, y+1));
		} else if (y==0) {
			rtn.add(getCell(x-1, y  ));
			rtn.add(getCell(x-1, y+1));
			rtn.add(getCell(x  , y+1));
			rtn.add(getCell(x+1, y  ));
			rtn.add(getCell(x+1, y+1));
		} else if (y==sizeY){
			rtn.add(getCell(x-1, y  ));
			rtn.add(getCell(x-1, y-1));
			rtn.add(getCell(x  , y-1));
			rtn.add(getCell(x+1, y  ));
			rtn.add(getCell(x+1, y-1));
		} else {
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
}
