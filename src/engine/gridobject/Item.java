package engine.gridobject;

import engine.Listable;
import engine.Statistic;

public abstract class Item extends GridObject implements Listable {

	private String myName;
	
	public Item(int x, int y, String name) {
		super(x, y);
		myName = name;
	}

	public String getName() {
		return myName;
	}

	public abstract void useItem();
	
	@Override
	public void display() {
		// write method to write name / picture to a Menu
		
	}
	
	public void changeStatistic(Statistic statistic, int amountToChange){
		statistic.changeValue(amountToChange);
	}
	
}
