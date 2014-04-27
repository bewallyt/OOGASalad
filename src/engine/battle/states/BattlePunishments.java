package engine.battle.states;

import engine.battle.BattleManager;

public class BattlePunishments implements BattleState {

	@Override
	public void doState(BattleManager manager) {
		manager.getPlayer().changeMoney(-manager.getEnemy().getMoney());
		manager.getPlayer().increaseExperience(-manager.getEnemy().getExperience());
		manager.setCurrentTextToBeDisplayed("You lost " + manager.getEnemy().getMoney() + " coins and "+
				manager.getEnemy().getExperience() + " experience.");
		manager.setCurrentState("BattleLost");

	}

}
