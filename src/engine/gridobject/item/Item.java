package engine.gridobject.item;

import engine.Listable;
import engine.Statistic;
import engine.gridobject.GridObject;
import engine.world.Tile;

public abstract class Item implements Listable {

	private String myName;
	private String myImageName;
	private Statistic myStatistic;
	private boolean useOnPlayer=false;
	private boolean useOnWeapon=false;
	
	public Item(String image, String name, Statistic statistic) {
		myName = name;
		myImageName = image;
		myStatistic = statistic;
	}

	public String getName() {
		return myName;
	}
	
	public void useOnPlayer(){
		useOnPlayer=true;
	}
	
	public void useOnWeapon(){
		useOnWeapon=true;
	}

	public abstract void useItem();
	
	@Override
	public void display() {
		// write method to write name / picture to a Menu
		
	}
	
	public void changeStatistic(int amountToChange){
		myStatistic.changeValue(amountToChange);
	}
	
}
