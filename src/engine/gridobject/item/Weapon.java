package engine.gridobject.item;

import engine.world.Tile;

public class Weapon extends Item{

	public Weapon(Tile tile, String image, String name, int numTilesWidth, int numTilesHeight) {
		super(image, name, numTilesWidth, numTilesHeight);	
		super.setDoesHarm(true);
	}

	@Override
	public void useItem() {
		// TODO Auto-generated method stub
		//Here I will use ProximityChecker() to determine onto whom the weapon should be used.
		//The ProximityChecker will NOT be necessary for Arena mode because animation patterns
		//are consistent. Modify HurtCollision? or use BumpCollision. Change the player's range
		//momentarily when they are attacking using a Weapon.
		
	}

}
