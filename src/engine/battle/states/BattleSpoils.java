package engine.battle.states;

import engine.battle.BattleManager;

public class BattleSpoils implements BattleState {

	@Override
	public void doState(BattleManager manager) {
		manager.getPlayer().changeMoney(manager.getEnemy().getMoney());
		manager.getPlayer().increaseExperience(manager.getEnemy().getExperience());
		manager.setCurrentTextToBeDisplayed("You recieved " + manager.getEnemy().getMoney() + " coins and "+
		manager.getEnemy().getExperience() + " experience.");
		manager.setCurrentState("BattleWon");
	}

}
