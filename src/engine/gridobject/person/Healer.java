package engine.gridobject.person;

import java.util.List;

import engine.dialogue.NPCResponseNode;
import engine.dialogue.UserQueryNode;

public class Healer extends NPC {

	public Healer(String[] animImages, String name, double speed, int numTilesWidth,
			int numTilesHeight, int movementType, Player player) {
		super(animImages, name, speed, numTilesWidth, numTilesHeight, movementType, player);
		NPCResponseNode n = new NPCResponseNode("You are fully healed", null);
		this.setResponseNode(n);
	}

	@Override
	public void doAction(){
		doDialogue();
		getPlayer().getStatsMap().get("health").setToMax();
	}
}
