package minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import cells.*;

public class Field implements IFieldObservable{

	private AbstractCell[][] field;
	private int sizeX,sizeY,mines,clues;
	private List<IFieldObserver> fos;
	
	public Field(int x, int y, int mines){
		
		fos=new ArrayList<>();
		this.sizeX=x; this.sizeY=y;		
		this.mines=mines;
		this.clues = (x*y)-mines;
		instanciateCellArray(x, y);
		setMines();
		setClues();
		
	}

	@Deprecated
	public AbstractCell getCell(int x, int y) {
		return getCell(new Location(x,y));
	}

	public AbstractCell getCell(Location c) {	
		return field[c.getX()][c.getY()];
	}
	@Deprecated
	public void clickCell(int x, int y){
		clickCell(new Location(x, y));
	}
	
	public void clickCell(Location c) {
	
		if (!getCell(c).isMine()) clues--;
		
		getCell(c).clickCell();
		if (getCell(c).getClue()==0) {
			List <Location> nighbors = getNeighborsOfCell(c);
			for (Location n : nighbors) {
				clickCell(n);
			}
		}
		updateObservers();
		
	}

	@Deprecated
	public void flagCell(int x, int y){
		field[x][y].toggleCellFlag();
		updateObservers();
	}
	public void flagCell(Location c){
		field[c.getX()][c.getY()].toggleCellFlag();
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
				rtn.append(field[i][j].getChar());
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

	private void setClues() {
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				
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
		int x = origin.getX(); int y = origin.getY();
		
		if((x==0&&y==0)){ 				//Northwest corner
			rtn.add(new Location(x  , y+1));
			rtn.add(new Location(x+1, y  ));
			rtn.add(new Location(x+1, y+1));
		} else if (x==0&&y==sizeY-1){		//Northeast corner
			rtn.add(new Location(x  , y-1));
			rtn.add(new Location(x+1, y  ));
			rtn.add(new Location(x+1, y-1));
		} else if (x==sizeX-1&&y==0){		//Southwest corner
			rtn.add(new Location(x  , y+1));
			rtn.add(new Location(x-1, y  ));
			rtn.add(new Location(x-1, y+1));
		} else if (x==sizeX-1&&y==sizeY-1){	//Southeast corner
			rtn.add(new Location(x  , y-1));
			rtn.add(new Location(x-1, y  ));
			rtn.add(new Location(x-1, y-1));
		} else if (x==0){				//North edge
			rtn.add(new Location(x  , y-1));
			rtn.add(new Location(x  , y+1));
			rtn.add(new Location(x+1, y  ));
			rtn.add(new Location(x+1, y-1));
			rtn.add(new Location(x+1, y+1));
		} else if (x==sizeX-1){			//South edge
			rtn.add(new Location(x  , y-1));
			rtn.add(new Location(x  , y+1));
			rtn.add(new Location(x-1, y  ));
			rtn.add(new Location(x-1, y-1));
			rtn.add(new Location(x-1, y+1));
		} else if (y==0) {				//West edge
			rtn.add(new Location(x-1, y  ));
			rtn.add(new Location(x-1, y+1));
			rtn.add(new Location(x  , y+1));
			rtn.add(new Location(x+1, y  ));
			rtn.add(new Location(x+1, y+1));
		} else if (y==sizeY-1){			//East edge
			rtn.add(new Location(x-1, y  ));
			rtn.add(new Location(x-1, y-1));
			rtn.add(new Location(x  , y-1));
			rtn.add(new Location(x+1, y  ));
			rtn.add(new Location(x+1, y-1));
		} else {						//middle
			rtn.add(new Location(x-1, y  ));
			rtn.add(new Location(x-1, y-1));
			rtn.add(new Location(x-1, y+1));
			rtn.add(new Location(x  , y-1));
			rtn.add(new Location(x  , y+1));
			rtn.add(new Location(x+1, y  ));
			rtn.add(new Location(x+1, y-1));
			rtn.add(new Location(x+1, y+1));
		}
		return rtn;
	}

	@Override
	public void regFieldObserver(IFieldObserver fo) {fos.add(fo); fo.update();}

	@Override
	public void remFieldObserver(IFieldObserver fo) {fos.remove(fo);}
	
	private void updateObservers(){
		for (IFieldObserver fo : fos) {
			fo.update();
		}
	}
}
