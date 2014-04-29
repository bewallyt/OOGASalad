package engine.gridobject.person;

import java.util.List;

import util.Constants;

import engine.dialogue.NPCResponseNode;
import engine.dialogue.UserQueryNode;

public class Healer extends NPC {

	public Healer(String[] animImages, String name, double speed,
			int numTilesWidth, int numTilesHeight, int movementType,
			Player player) {
		super(animImages, name, speed, numTilesWidth, numTilesHeight,
				movementType, player);
		NPCResponseNode n = new NPCResponseNode("You are fully healed", null);
		this.setResponseNode(n);
	}

	@SuppressWarnings("unchecked")
	public Healer(List<Object> list) {
		this((String[]) ((List<String>) list.get(Constants.IMAGE_CONST))
				.toArray(new String[12]), "Healer", 1, (int) ((Double) list
				.get(Constants.WIDTH_CONST)).intValue(), (int) ((Double) list
				.get(Constants.HEIGHT_CONST)).intValue(), 1, (Player) list
				.get(Constants.HEALER_PLAYER_CONST));
	}

	@Override
	public void doAction() {
		doDialogue();
		getPlayer().getStatsMap().get("health").setToMax();
	}
}
