package engine.item;

import engine.battle.BattleExecutable;
import engine.gridobject.person.Player;

public abstract class Item extends Pickupable implements BattleExecutable {

	
	public Item(String image, String name) {
		super(name, image);
	}

	public abstract void useItem();
	
	@Override
	public void pickUp(Player player){
		player.addItem(this);
	}
	
//	public String toString() {
//		return super.getName();
//	}
	
}
