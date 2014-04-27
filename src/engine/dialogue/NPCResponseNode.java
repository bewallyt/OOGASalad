package engine.dialogue;

import java.util.ArrayList;
import java.util.List;

import engine.gridobject.GridObject;
import engine.item.Item;
import engine.gridobject.person.*;

public class NPCResponseNode {

	private String myDialogue;
//	private Map<UserQueryNode, NPCResponseNode> myChildren;
	private List<UserQueryNode> myChildren;
	private GridObject myNPC;
	private Item myItem;
	
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
		// just gets first item in list for now
//		if (!((NPC) myNPC).getItems().isEmpty()) {
//			return ((NPC) myNPC).getItems().get(0);
//		}
//		return null;
		
		if (myItem != null) {
			return myItem;
		}
		
		return null;
	}
	
	

}
