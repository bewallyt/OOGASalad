package engine.world;

import engine.battle.BattleManager;

public class ArenaWorldLooper extends GameLooper {

	ArenaWorld myWorld;
	BattleManager myBattleManager;
	public ArenaWorldLooper(World currentWorld) {
		super(currentWorld);
		myWorld = (ArenaWorld) getWorld();
		myBattleManager = new BattleManager(myWorld.getPlayer(),myWorld.getEnemy());
	}

	@Override
	public World doLoop() {
		if(myWorld.getEnemy().getStatsMap().get("health").getValue()==0){
			return myWorld.getPrevWorld();
		}
		return null;
	}

}
