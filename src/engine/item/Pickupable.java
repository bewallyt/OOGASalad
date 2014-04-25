package engine.item;

import engine.dialogue.NPCResponseNode;
import engine.gridobject.person.Player;


public abstract class Pickupable {

	private String myName;
	private String myImageName;
	private NPCResponseNode  myResponseNode;
	
	public Pickupable(String name, String image) {
		myName = name;
		myImageName = image;
		myResponseNode = new NPCResponseNode(null, "You found " + name + "!");
	}
	

	public String toString() {
		return myName;
	}
	
	/**
	 * Pick up an object.
	 *
	 * @param player the player to pick up the object
	 */
	public abstract void pickUp(Player player);
	
	
}
