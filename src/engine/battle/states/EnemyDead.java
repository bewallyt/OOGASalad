package engine.battle.states;

import engine.battle.BattleManager;

public class EnemyDead implements BattleState {

	@Override
	public void doState(BattleManager manager) {
		manager.setCurrentTextToBeDisplayed("You have defeated " + manager.getEnemy().toString());
		manager.setCurrentState("BattleWon");
	}

}
