package engine.battle.states;

import engine.battle.BattleManager;
import engine.dialogue.BattleExecutorNode;
import engine.dialogue.BattleSelectorNode;
import engine.dialogue.InteractionMatrix;

public class TopLevel implements BattleState{

	private BattleSelectorNode myCurrentBattleSelector;
	private InteractionMatrix myOptions;

	public TopLevel(BattleSelectorNode currentBattleSelector, InteractionMatrix options){
		myCurrentBattleSelector = currentBattleSelector;
		myOptions = options;
	}

	@Override
	public int doState() {
		int count=0;
		myCurrentBattleSelector.getChildren().size();
		for(int i=0; i<myOptions.getDimension()[0]; i++){
			for(int j=0; j<myOptions.getDimension()[1]; j++){
				if(myCurrentBattleSelector.getChildren().size()>count)
					myOptions.setNode(myCurrentBattleSelector.getChildren().get(count), i, j);
				else{
					myOptions.setNode(null, i, j);
				}
				count++;
			}
		}
		return BattleManager.BOTTOMLEVEL;		
	}

	public BattleExecutorNode getCurrentBattleExecutorNode(){
		return (BattleExecutorNode) myOptions.getCurrentNode();
	}

}
