package engine.battle.states;

import engine.battle.BattleManager;

public class BattleWon implements BattleState {

	@Override
	public void doState(BattleManager manager) {
		manager.setCurrentState("ExitWon");

	}

}
