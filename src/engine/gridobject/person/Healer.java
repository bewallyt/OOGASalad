package engine.gridobject.person;

import java.util.List;

public class Healer extends NPC {

	public Healer(String[] animImages, String name, double speed, int numTilesWidth,
			int numTilesHeight, int movementType, Player player) {
		super(animImages, name, speed, numTilesWidth, numTilesHeight, movementType, player);
	}
	
//	public Healer(List<Object> list) {
//		super((String[]) list.get(1), (int) list.get(4), (int) list.get(2),
//				(int) list.get(3), (int) list.get(5), (Player) list.get(6));
//	}

	
	@Override
	public void doAction(){
		doDialogue();
		getPlayer().getStatsMap().get("health").setToMax();
	}
}
