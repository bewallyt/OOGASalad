package engine.world;

import engine.battle.BattleManager;
import engine.state.BattleState;

public class ArenaWorldLooper extends GameLooper {

	ArenaWorld myWorld;
	BattleManager myBattleManager;
	public ArenaWorldLooper(ArenaWorld currentWorld) {
		super(currentWorld);
		myWorld = (ArenaWorld) getWorld();
		myBattleManager = new BattleManager(myWorld.getPlayer(),myWorld.getEnemy());
		myWorld.getPlayer().setState(new BattleState(myBattleManager));
	}

	@Override
	public World doLoop() {
		if(myWorld.getEnemy().getStatsMap().get("health").getValue()==0){
			return myWorld.getPrevWorld();
		}
		return null;
	}

}
