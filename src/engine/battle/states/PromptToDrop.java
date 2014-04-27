package engine.battle.states;

import engine.battle.BattleManager;

public class PromptToDrop implements BattleState {

	@Override
	public void doState(BattleManager manager) {
		
		manager.setCurrentState("BottomLevel");

	}

}
