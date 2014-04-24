package engine.gridobject.person;

import java.util.List;

import engine.ProximityChecker;
import engine.battle.Attack;
import engine.world.ArenaWorld;



public class Enemy extends NPC {
	private boolean battleOnSight=false;
	private boolean battleInitiated=false;
	private ArenaWorld myWorld;
	boolean wasBattled=false;
	private boolean isRandom;


	/**
	 * Instantiates a new enemy.
	 *
	 * @param animImages the anim images (must be 12)
	 * @param speed the speed of the enemy
	 * @param numTilesWidth the width in tiles
	 * @param numTilesHeight the height in tiles
	 * @param movementType the movement type. 1=back and forth 2=follow player 3=stand still
	 * @param player the player
	 */
	public Enemy(String[] animImages, String name, double speed, int numTilesWidth, int numTilesHeight, int movementType, Player player) {
		super(animImages,name, speed, numTilesWidth, numTilesHeight,movementType,player);
		isRandom=false;
	}
	
//	public Enemy(List<Object> list) {
//		super();
//
//	}



	/**
	 * Sets the arena world that should be created if a battle is initiated.
	 *
	 * @param world the new world
	 */
	public void setWorld(ArenaWorld world){
		myWorld = world;
	}

	public ArenaWorld getWorld(){
		battleInitiated=false;
		return myWorld;
	}

	/**
	 * This enemy initiates a battle if the player is in sight.
	 */
	public void doBattleOnSight(){
		battleOnSight=true;
	}

	public boolean isBattle(){
		battleInitiatedOnSight();
		return battleInitiated;
	}

	public void battleInitiatedOnSight(){
		if(battleOnSight && inSight()){	
			doAction();
		}
	}

	@Override 
	public void doAction(){
		//doDialogue();
		
		battleInitiated=true;
	}

	private boolean inSight(){
		if(Math.abs(ProximityChecker.isLeftProximity(this, getPlayer()))<100 && getFacing() == 1)
			return true;
		if(Math.abs(ProximityChecker.isRightProximity(this,  getPlayer()))<100 && getFacing()==3)
			return true;
		if(Math.abs(ProximityChecker.isTopProximity(this, getPlayer()))<100 && getFacing()==2)
			return true;
		if(Math.abs(ProximityChecker.isBottomProximity(this, getPlayer()))<100 && getFacing()==0)
			return true;
		return false;
	}
	public boolean getWasBattled(){
		return wasBattled;
	}
	public void setWasBattled(){
		wasBattled=true;
	}

	public void setRandom() {
		isRandom=true;	
	}
	public boolean isRandom(){
		return isRandom;
	}
	
	



}