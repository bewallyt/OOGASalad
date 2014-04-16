package engine.world;

import java.util.ArrayList;
import java.util.List;

import engine.ProximityChecker;
import engine.gridobject.GridObject;
import engine.gridobject.person.Player;

public class SurroundingChecker {

	private WalkAroundWorld myWorld;

	public SurroundingChecker(WalkAroundWorld w) {
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
	public List<GridObject> checkSurroundings(Player player) {
//		System.out.println(player.getY());
		List<GridObject> goList = new ArrayList<GridObject>();
		for(GridObject go : myWorld.getGridObjectList()){
//			if(go instanceof Door){
//				System.out.println(player.getY());
//				System.out.println(ProximityChecker.isBottomProximity(player, go));
//			}
			if(ProximityChecker.isLeftProximity(player, go)==2 && player.getFacing()==1 )
				goList.add(go);
			if(ProximityChecker.isRightProximity(player, go)==-2 && player.getFacing()==3 ){
				goList.add(go);	}
			if(ProximityChecker.isBottomProximity(player, go)==-2 && player.getFacing()==0){
				goList.add(go);		}
			if(ProximityChecker.isTopProximity(player, go)==2 && player.getFacing()==2){
				goList.add(go);		}
		}
		if (goList.isEmpty()) goList.add(null);
		return goList;

	}


}
