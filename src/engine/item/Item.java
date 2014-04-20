package engine.item;

import engine.battle.BattleExecutable;
import engine.gridobject.Pickupable;
import engine.gridobject.person.Player;

public abstract class Item implements Pickupable, BattleExecutable {

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
	public void pickUp(Player player){
		player.addItem(this);
	}
	
}
