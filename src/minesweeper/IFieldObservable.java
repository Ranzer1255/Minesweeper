package minesweeper;

public interface IFieldObservable {
	
	public void regFieldObserver(IFieldObserver fo);
	
	public void remFieldObserver(IFieldObserver fo);
	
}
