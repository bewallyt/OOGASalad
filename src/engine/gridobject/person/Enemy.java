package engine.gridobject.person;

import engine.ProximityChecker;



public class Enemy extends NPC {
	private boolean battleOnTalk=false;
	private boolean battleOnSight=false;
	private boolean battleInitiated=false;

	public Enemy(String[] animImages, double speed, int numTilesWidth, int numTilesHeight, int movementType, Player player) {
		super(animImages,speed, numTilesWidth, numTilesHeight,movementType,player);
	}

	public void battleOnTalk(){
		battleOnTalk=true;
	}

	public void battleOnSight(){
		battleOnSight=true;
	}

	public boolean getBattleOnTalk(){
		return battleOnTalk;
	}

	public boolean getBattleOnSight(){
		return battleOnSight;
	}

	public boolean battleInitiated(){
		if(battleOnSight && inSight()){
			battleInitiated=true;
			return true;
		}
		
//		else if(battleOnTalk && talked)
		return false;
	}

	public boolean inSight(){
		if(Math.abs(ProximityChecker.isLeftProximity(this, getPlayer()))<100 && 
				ProximityChecker.isTopProximity(this, getPlayer())<=2 && getFacing() == 1)
			return true;
		if(Math.abs(ProximityChecker.isRightProximity(this,  getPlayer()))<100 && 
				ProximityChecker.isRightProximity(this, getPlayer())>=-2 && getFacing()==3)
			return true;
		if(Math.abs(ProximityChecker.isTopProximity(this, getPlayer()))<100 && 
				ProximityChecker.isTopProximity(this, getPlayer())<=2 && getFacing()==2)
			return true;
		if(Math.abs(ProximityChecker.isBottomProximity(this, getPlayer()))<100 && 
				ProximityChecker.isTopProximity(this, getPlayer())>=-2 && getFacing()==0)
			return true;
		return false;
	}



}
