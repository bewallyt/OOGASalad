package engine.world;

import engine.ProximityChecker;
import engine.gridobject.GridObject;
import engine.gridobject.person.NPC;
import engine.gridobject.person.Player;

public class SurroundingChecker {

	private World myWorld;

	public SurroundingChecker(World w) {
		myWorld = w;
	}

	/**
	 * A method that will determine if there is an object on the tile in the direction that
	 * the player is facing. It will then return this GridObject to the Player class such that 
	 * the Player class can invoke the dialogue.
	 * 
	 * @param player the player who's surrounding will be checked
	 * @return the object that it is facing
	 */
	public GridObject checkSurroundings(Player player) {

		for(GridObject go : myWorld.getGridObjectList()){
			if(ProximityChecker.isLeftProximity(player, go) && player.getXFacing()==1 )
				return go;
			if(ProximityChecker.isRightProximity(player, go) && player.getXFacing()==0 ){
				return go;		}
			if(ProximityChecker.isBottomProximity(player, go) && player.getYFacing()==1){
				return go;		}
			if(ProximityChecker.isTopProximity(player, go) && player.getYFacing()==0){
				return go;		}
		}


		return null;
	}


}
