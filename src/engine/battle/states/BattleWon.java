package engine.battle.states;

import engine.battle.BattleManager;

public class BattleWon implements BattleState {

	@Override
	public void doState(BattleManager manager) {
		if(manager.getPlayer().getWeaponList().size()>2){
			manager.setBattleSelector();
			manager.setCurrentState("PromptToDrop");
			manager.setCurrentTextToBeDisplayed("You have too many weapons! Choose a weapon to drop:");
			int count=0;

			for(int i=0; i<manager.getMatrix().getDimension()[0]; i++){
				for(int j=0; j<manager.getMatrix().getDimension()[1]; j++){
					if(manager.getCurrentBattleSelector().getChildren().size()>count)
						manager.getMatrix().setNode(manager.getCurrentBattleSelector().getChildren().get(count), i, j);
					else{
						manager.getMatrix().setNode(null, i, j);
					}
					count++;
				}
			}
		}
		else{manager.setCurrentState("ExitWon");}

	}

}
