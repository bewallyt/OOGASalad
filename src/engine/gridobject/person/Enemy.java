package engine.gridobject.person;



public abstract class Enemy extends NPC {
	
	
	
	public Enemy(String image, double speed, int numTilesWidth, int numTilesHeight, int movementType, Player player) {
		super(image,speed, numTilesWidth, numTilesHeight,movementType,player);
		
	}
//
//	@Override
//	public abstract void uniqueMove();
	
	
}
