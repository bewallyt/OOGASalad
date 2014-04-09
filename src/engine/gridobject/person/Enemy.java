package engine.gridobject.person;

import engine.ProximityChecker;



public abstract class Enemy extends NPC {
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
		if(battleOnSight && inSight())
			battleInitiated=true;
//		else if(battleOnTalk && talked)
		return false;
	}

	public boolean inSight(){
		if(ProximityChecker.isLeftProximity(this, getPlayer())<20 && getFacing() == 1)
			return true;
		if(ProximityChecker.isRightProximity(this,  getPlayer())<20 && getFacing()==3)
			return true;
		if(ProximityChecker.isTopProximity(this, getPlayer())<20 && getFacing()==2)
			return true;
		if(ProximityChecker.isBottomProximity(this, getPlayer())<20 && getFacing()==0)
			return true;
		return false;
	}



}
