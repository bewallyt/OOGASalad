package engine.battle.states;

import engine.battle.BattleManager;

public class PlayerDead implements BattleState{

	@Override
	public void doState(BattleManager manager) {
		manager.setCurrentTextToBeDisplayed("You have been defeated!");
		manager.setCurrentState("BattleLost");
	}

}
