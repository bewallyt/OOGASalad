package engine.gridobject;

import engine.gridobject.person.Player;


public interface Pickupable {

	/**
	 * Pick up an object.
	 *
	 * @param player the player to pick up the object
	 */
	public void pickUp(Player player);
}
