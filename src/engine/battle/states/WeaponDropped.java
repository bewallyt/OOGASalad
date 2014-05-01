package engine.battle.states;

import engine.battle.BattleManager;

public class WeaponDropped implements BattleState {

	@Override
	public void doState(BattleManager manager) {
		manager.setCurrentTextToBeDisplayed("You have defeated " + manager.getEnemy().toString() + " and he dropped his " + manager.getLabels()[1]);
		manager.setDidDrop();
		manager.setCurrentState("BattleSpoils");
	}

}
