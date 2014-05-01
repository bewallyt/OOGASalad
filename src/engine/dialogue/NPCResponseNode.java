package engine.dialogue;

import java.util.ArrayList;
import java.util.List;

import engine.gridobject.GridObject;
import engine.item.Item;
import engine.gridobject.person.*;

/**
 * One class that is crucial to our conversation design. This NPCResponse node is responsible for the text that 
 * is said by an NPC. It is also responsible for giving an item (if chosen to do so) to the Player.
 *
 */
public class NPCResponseNode {

	private String myDialogue;
//	private Map<UserQueryNode, NPCResponseNode> myChildren;
	private List<UserQueryNode> myChildren;
	private GridObject myNPC;
	private Item myItem;
	
	/**
	 * Holds info for what NPC says in a conversation
	 * @param dialogue
	 * @param it item that will be given to Player, should be <code>null</code> if no item
	 */
	public NPCResponseNode(String dialogue, Item it) {
		//myNPC = n;
		myDialogue = dialogue;
		myChildren = new ArrayList<UserQueryNode>();
		myItem = it;
	}
	
	/**
	 * Method to add a response to a given node. Put <code>null</code> if you want the 
	 * field to be blank when displayed and unselectable.
	 * @param uqNode
	 */
	public void addResponseNode(UserQueryNode uqNode) {
		myChildren.add(uqNode);
	}

	
	public List<UserQueryNode> getUserQueryNodes() {
		return myChildren;
	}
	
	public String getDialogue() {
		return myDialogue;
	}
	
	public Item getItem() {
		
		if (myItem != null) {
			return myItem;
		}
		
		return null;
	}
	
	

}
