package engine.battle.states;

import engine.battle.BattleManager;

public class BackToTop implements BattleState{

	@Override
	public void doState(BattleManager manager) {
		manager.backToTopOfBattle();
		
	}

}
