package engine.battle.states;

import engine.battle.BattleManager;

public class ExitLost implements BattleState{

	@Override
	public void doState(BattleManager manager) {
		manager.setCurrentState("ExitLost");
		
	}

}
