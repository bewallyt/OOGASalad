package engine.gridobject.person;

import java.util.List;
import java.util.Map;

import util.Constants;
import engine.ProximityChecker;
import engine.battle.Attack;
import engine.world.ArenaWorld;



public class Enemy extends NPC {
	private boolean battleOnSight=false;
	private boolean battleInitiated=false;
	private ArenaWorld myWorld;
	boolean wasBattled=false;
	private boolean isRandom;
	private boolean wasTalked=false;
	private int myExperience=0;
	


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
	
	@SuppressWarnings("unchecked")
	public Enemy(List<Object> list) {
		super((String[]) ((List<String>) list.get(Constants.IMAGE_CONST))
				.toArray(new String[12]), (String) list
				.get(Constants.NAME_CONST), Constants.SPEED,
				(int) ((Double) list.get(Constants.WIDTH_CONST)).intValue(),
				(int) ((Double) list.get(Constants.HEIGHT_CONST)).intValue(),
				(int) ((Double) list.get(Constants.ENEMY_MOVEMENT_CONST)).intValue(),
				(Player) list.get(Constants.ENEMY_PLAYER_CONST));
		
		Map<String, Double> startVals = (Map<String, Double>) list.get(Constants.VALUES_CONST);
		addAllStatistics(startVals);
		
		String[] weapons = (String[]) ((List<String>) list.get(Constants.WEAPONS_CONST)).toArray(new String[(int) ((Double) list.get(Constants.WEP_LENGTH_CONST)).intValue()]);
		// need a map of all of the weapons
//		addAllWeapons(null, weapons);
		
		isRandom = false;
		// will probably have to set doBattleOnSight() as default
//		doBattleOnSight();
	}




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
		if(!wasBattled && !wasTalked){
			doDialogue();
			battleInitiated=true;
			wasTalked=true;
		}
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
		wasTalked=true;
	}

	public void setRandom() {
		isRandom=true;	
	}
	public boolean isRandom(){
		return isRandom;
	}

	public void setExperience(int experience){
		myExperience=experience;
	}
	
	public int getExperience(){
		return myExperience;
	}
}