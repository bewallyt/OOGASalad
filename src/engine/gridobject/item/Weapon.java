package engine.gridobject.item;

import engine.world.Tile;

public class Weapon extends Item{

	public Weapon(Tile tile, String image, String name) {
		super(image, name);	
		super.setDoesHarm(true);
	}

	@Override
	public void useItem() {
		// TODO Auto-generated method stub
		
	}

}
