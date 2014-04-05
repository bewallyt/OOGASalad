package engine.gridobject.person;

public abstract class Movement {
	private NPC myNPC;
	private Player myPlayer;
	public Movement(NPC npc, Player player){
		myNPC = npc;
		myPlayer = player;
	}
	
	public abstract void move();

	public Player getPlayer(){
		return myPlayer;
	}
	
	public NPC getNPC(){
		return myNPC;
	}
}
