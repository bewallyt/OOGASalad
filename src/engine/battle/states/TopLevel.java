package engine.battle.states;

import engine.battle.BattleManager;
import engine.dialogue.BattleExecutorNode;

public class TopLevel implements BattleState{


	@Override
	public void doState(BattleManager manager) {
		System.out.println(manager.getCurrentBattleSelector());
		if(manager.getCurrentBattleSelector().getChildren().size()>0){
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
			manager.setCurrentBattleExecutorNode((BattleExecutorNode) manager.getMatrix().getCurrentNode());
			manager.setCurrentState("BottomLevel");
		}

	}



}
