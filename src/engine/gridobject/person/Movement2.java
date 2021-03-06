package engine.gridobject.person;

public class Movement2 extends Movement{

	/**
	 * Instantiates a new player approaching movement.
	 *
	 * @param npc the npc
	 * @param player the player
	 */
	public Movement2(NPC npc, Player player) {
		super(npc, player);
	}

	@Override
	public void move() {
		approachPlayer(getPlayer());
		
	}
	
	public void approachPlayer(Player player){
//		System.out.println(getNPC().howFarFromPlayer());
		if(getNPC().howFarFromPlayer()<150){	
			if(getNPC().getX()<player.getX())
				getNPC().setDX(Math.abs(getNPC().getSpeed()));
			else{getNPC().setDX(-Math.abs(getNPC().getSpeed()));}

			if(getNPC().getY()<player.getY())
				getNPC().setDY(Math.abs(getNPC().getSpeed()));
			else{getNPC().setDY(-Math.abs(getNPC().getSpeed()));}
		}
		
		else{
			getNPC().setDX(0);
			getNPC().setDY(0);
		}
	}

}
