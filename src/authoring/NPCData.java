package authoring;

import java.util.List;

import util.Constants;

public class NPCData extends GridObjectData{
	private NPCResponseNode myRoot;
	public NPCData(int x, int y, String image, NPCResponseNode root) {
		super(x, y, image, root, Constants.NPC);
		myRoot = root;
		init();
	}
	
	public NPCResponseNode getDialogue(){
		return myRoot;
	}
}
