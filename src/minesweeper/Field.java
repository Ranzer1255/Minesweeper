package minesweeper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Field {

	
	private Cell[][] field;
	private int sizeX,sizeY,mines;
	
	public Field(int x, int y, int mines){
		
		this.sizeX=x; this.sizeY=y;
		
		field=new Cell[x][y];
		for (int i1 = 0; i1 < x; i1++) {
			for (int j1 = 0; j1 < y; j1++) {
				field[i1][j1] = new Cell();
			}
		}
		
		//set mines
		this.mines=mines;
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
	
		//set clues
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

	public Cell getCell(int x, int y) {
		
		return field[x][y];
	}

	private ArrayList<Cell> getNeighborsOfCell(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	private Cell getRandomCell() {
		return this.getCell(randomInt(this.sizeX+1), randomInt(this.sizeY+1));
	}

	private int randomInt(int i) {
		return ThreadLocalRandom.current().nextInt(i);
	}
}
