package engine.gridobject.item;

import engine.Listable;
import engine.Statistic;
import engine.gridobject.GridObject;
import engine.world.Tile;

public abstract class Item extends GridObject implements Listable {

	private String myName;
	
	public Item(String image, String name, int numTiles) {
		super(image, numTiles);
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
