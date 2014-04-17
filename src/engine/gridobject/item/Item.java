package engine.gridobject.item;

import engine.Listable;
import engine.Statistic;
import engine.gridobject.GridObject;
import engine.world.Tile;

public abstract class Item implements Listable {

	private String myName;
	private String myImageName;

	
	public Item(String image, String name) {
		myName = name;
		myImageName = image;
	}

	public String getName() {
		return myName;
	}

	public abstract void useItem();
	
	@Override
	public void display() {
		// write method to write name / picture to a Menu
		
	}
	

	
}
