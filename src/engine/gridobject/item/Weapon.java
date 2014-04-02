package engine.gridobject.item;

import engine.world.Tile;

public class Weapon extends Item{

	public Weapon(Tile tile, String image, String name, int numTiles) {
		super(image, name, numTiles);	
		super.setDoesHarm(true);
	}

	@Override
	public void useItem() {
		// TODO Auto-generated method stub
		
	}

}
