package engine.battle.states;

import engine.battle.BattleManager;

public class BattleLost implements BattleState {

	@Override
	public void doState(BattleManager manager) {
		manager.setCurrentState("ExitLost");

	}

}
