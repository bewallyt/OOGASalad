package engine.gridobject.person;

public class Healer extends NPC {

	public Healer(String[] animImages, double speed, int numTilesWidth,
			int numTilesHeight, int movementType, Player player) {
		super(animImages, speed, numTilesWidth, numTilesHeight, movementType, player);
	}
	
	@Override
	public void doAction(){
		doDialogue();
		getPlayer().getStatsMap().get("health").setToMax();
	}
}
