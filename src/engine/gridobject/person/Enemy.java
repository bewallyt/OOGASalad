package engine.gridobject.person;

import engine.ProximityChecker;



public class Enemy extends NPC {
	private boolean battleOnSight=false;
	private boolean battleInitiated=false;

	public Enemy(String[] animImages, double speed, int numTilesWidth, int numTilesHeight, int movementType, Player player) {
		super(animImages,speed, numTilesWidth, numTilesHeight,movementType,player);
	}

	public void battleOnSight(){
		battleOnSight=true;
	}

	public boolean isBattle(){
		return battleInitiated;
	}

	public void battleInitiatedOnSight(){
		if(battleOnSight && inSight()){
			doAction();
		}
	}

	@Override 
	public void doAction(){
		doDialogue();
		battleInitiated=true;
		System.out.println("battle");
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
