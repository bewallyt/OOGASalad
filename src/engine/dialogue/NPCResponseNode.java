package engine.dialogue;

import java.util.*;

import engine.gridobject.person.NPC;
import engine.item.Item;

public class NPCResponseNode {

	private String myDialogue;
//	private Map<UserQueryNode, NPCResponseNode> myChildren;
	private List<UserQueryNode> myChildren;
	private NPC myNPC;
	
	public NPCResponseNode(NPC n, String dialogue) {
		myNPC = n;
		myDialogue = dialogue;
		myChildren = new ArrayList<UserQueryNode>();
	}
	
	
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
		return myNPC.getItems().get(0);
	}
	
	

}
