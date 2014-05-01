package engine.battle.states;

import util.Constants;
import engine.battle.BattleManager;

public class EnemyDead implements BattleState {

	@Override
	public void doState(BattleManager manager) {
		manager.setCurrentTextToBeDisplayed("You have defeated " + manager.getEnemy().toString());
		manager.setCurrentState("BattleSpoils");
//		System.out.println(manager.getPlayer().getStatsMap().get(Constants.SPEED).getValue());
		manager.resetStats();
		//System.out.println(manager.getPlayer().getStatsMap().get(Constants.SPEED).getValue());
	}

}
