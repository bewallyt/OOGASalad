package engine.world;

import java.util.List;

import engine.gridobject.person.Player;
import engine.gridobject.*;

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
		// todo: write code that will determine, based on players heading, if there is an object
		// in front of it
		
		return null;
	}

}
